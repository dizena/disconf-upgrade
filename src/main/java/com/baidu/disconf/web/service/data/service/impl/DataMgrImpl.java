package com.baidu.disconf.web.service.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.disconf.web.service.app.dao.AppDao;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.dao.AreaDao;
import com.baidu.disconf.web.service.config.dao.ConfigDao;
import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.dao.DataDao;
import com.baidu.disconf.web.service.data.service.DataMgr;
import com.baidu.disconf.web.service.env.dao.EnvDao;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;
import com.baidu.wonder.other.PropUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DataMgrImpl implements DataMgr {
	public static Logger log = LoggerFactory.getLogger(DataMgrImpl.class);

	@Autowired
	private DataDao dataDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private AppDao appDao;

	@Autowired
	private EnvDao envDao;

	@Autowired
	private ConfigDao configDao;


	private Long area_id = PropUtils.getLocalAreaId();

	@Override
	public List<Data> getDataList() {
		List<Data> datas = new ArrayList<>();
		// 1,本区域的数据
		datas.add(getData());

		// 2,其他区域的
		List<Area> areas = areaDao.findAll();
		if (areas != null && !areas.isEmpty()) {
			for (Area area : areas) {
				if (area.getId() != area_id) {
					// 进行请求查询
					DisconfRemoteBizDataApi api = new DisconfRemoteBizDataApi(area.getHostport());
					if (api.session()||api.login(area.getName(), area.getPassword())) {
						Data d = api.getData();
						if (d != null) {
							datas.add(d);
						}
						api.close();
					}else{
						Data data =new Data();
						data.setAreaId(area.getId());
						data.setHostport(area.getHostport());
						data.setAreaCount(0);
						data.setAppCount(0);
						data.setEnvCount(0);
						data.setCfgCount(0);
						datas.add(data);
						log.error(area.getHostport()+" not connect with "+area.getName());
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
		if (area != null) {
			data.setAreaId(area_id);
			data.setHostport(area.getHostport());
			data.setAreaCount(areaCount);
			data.setAppCount(appCount);
			data.setEnvCount(envCount);
			data.setCfgCount(cfgCount);
		}
		return data;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int exec(String sql) {
		return dataDao.exec(sql);
	}

	@Override
	public List<String> getTabs() {
		//return dataDao.getTabs();
		List<String> list=new ArrayList<>();
		list.add("area");
		list.add("app");
		list.add("config");
		return list;
	}

	@Override
	public List<DataSql> getDatas(String tab) {
		return dataDao.getDatas(tab);
	}

}
