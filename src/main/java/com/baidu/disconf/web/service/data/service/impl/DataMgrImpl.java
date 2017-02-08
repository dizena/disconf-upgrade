package com.baidu.disconf.web.service.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.disconf.web.service.app.dao.AppDao;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.dao.AreaDao;
import com.baidu.disconf.web.service.config.dao.ConfigDao;
import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.service.DataMgr;
import com.baidu.disconf.web.service.env.dao.EnvDao;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;
import com.baidu.wonder.other.PropUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DataMgrImpl implements DataMgr {

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private AppDao appDao;
	
	@Autowired
    private EnvDao envDao;
	
	@Autowired
	private ConfigDao configDao;
	
	private String areaid = PropUtils.getKey("localArea");
	
	private Long area_id = Long.parseLong(areaid);
	
	@Override
	public List<Data> getDataList() {
		List<Data> datas = new ArrayList<>();
		// 1,本区域的数据
		datas.add(getData());

		//2,其他区域的
		List<Area> areas = areaDao.findAll();
		if(areas!=null&&!areas.isEmpty()){
			for (Area area : areas) {
				if(area.getId()!=area_id){
					//进行请求查询
					DisconfRemoteBizDataApi api=new DisconfRemoteBizDataApi(area.getHostport());
					if(api.login(area.getName(), area.getPassword())){
						Data d=api.getData();
						if(d!=null){
							datas.add(d);
						}
					}
				}
			}
		}
		
		return datas;
	}

	public Data getData() {
		Data data = new Data();
		// 1 区域数据
		int areaCount = areaDao.getCount();
		int appCount = appDao.getCount();
		int envCount = envDao.getCount();
		int cfgCount = configDao.getCount();
		
		Area area = areaDao.get(area_id);
		if(area!=null){
			data.setAreaId(area_id);
			data.setHostport(area.getHostport());
			data.setAreaCount(areaCount);
			data.setAppCount(appCount);
			data.setEnvCount(envCount);
			data.setCfgCount(cfgCount);
		}
		return data;
	}

}
