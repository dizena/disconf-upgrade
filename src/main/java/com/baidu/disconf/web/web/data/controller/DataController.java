package com.baidu.disconf.web.web.data.controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.service.AreaMgr;
import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.service.DataMgr;
import com.baidu.dsp.common.constant.ErrorCode;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;
import com.baidu.wonder.other.DisconfRemoteBizItemApi;

@Controller
@RequestMapping(WebConstants.API_PREFIX + "/data")
public class DataController  extends BaseController {
	
	@Autowired
    private DataMgr dataMgr;
	
	@Autowired
	private AreaMgr areaMgr;
	
	@RequestMapping(value = "/getCount", method = RequestMethod.GET)
    @ResponseBody
    public Data getCount() {
		Data d = dataMgr.getData();
    	return d;
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase list() {
		List<Data> ds = dataMgr.getDataList();
    	return buildListSuccess(ds, ds.size());
    }
	
	
	@RequestMapping(value = "/sync", method = RequestMethod.POST)
    @ResponseBody
    public Object sync(@RequestParam("a") Long a,@RequestParam("") Long b) {
		Area areaA = areaMgr.getArea(a);
		Area areaB = areaMgr.getArea(b);
		
		//取数据
		List<DataSql> datas=null;
		DisconfRemoteBizDataApi api1=new DisconfRemoteBizDataApi(areaA.getHostport());
		if(api1.session()||api1.login(areaA.getName(), areaA.getPassword())){
			datas= api1.db2Api();
			api1.close();
		}
		
		//传数据
		DisconfRemoteBizDataApi api2=new DisconfRemoteBizDataApi(areaB.getHostport());
		if(api2.session()||api2.login(areaB.getName(), areaB.getPassword())){
			if(datas!=null){
				api2.api2Db(datas);
			}
			api2.close();
		}
		
		
		//做通知
		DisconfRemoteBizItemApi api3=new DisconfRemoteBizItemApi(areaB.getHostport());
		if(api3.session()||api3.login(areaB.getName(), areaB.getPassword())){
			api3.notifySome();
			api3.close();
		}
		//Long local =PropUtils.getLocalAreaId();
		
		return 1;
    }
	
	//多服务调用使用线程池；
	
	//1,DB--->API
	@RequestMapping(value = "/db2Api", method = RequestMethod.GET)
	public void db2Api(HttpServletResponse response){
		try {
			List<DataSql> all=new ArrayList<>();
			List<String> tabs = dataMgr.getTabs();
			for (String tab : tabs) {
				List<DataSql> datas= dataMgr.getDatas(tab);
				all.addAll(datas);
			}
			// 传送到客户端
			//response.setContentType("application/zip");
			//response.setHeader("content-disposition","attachment;filename=disconfdb.zip");
			response.setCharacterEncoding("UTF-8");
			ObjectOutputStream objout=new ObjectOutputStream(response.getOutputStream());
			objout.writeObject(all);
			objout.flush();
			objout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//2,API--->DB
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api2Db", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjectBase api2Db(HttpServletRequest request){
		try {
			InputStream ins =request.getInputStream();
			ObjectInputStream objins=new ObjectInputStream(ins);
			Object obj = objins.readObject();
			
			List<DataSql> datas=(List<DataSql>) obj;
			for (DataSql dataSql : datas) {
				try{
					dataMgr.exec(dataSql.getInsertSql());
				}catch(Exception e){
					dataMgr.exec(dataSql.getUpdateSql());
				}
			}
			objins.close();
			ins.close();
			return buildSuccess("创建成功");
		} catch (Exception e) {
			e.printStackTrace();
			return buildGlobalError("同步失败", ErrorCode.DAO_ERROR);
		}
	}
}
