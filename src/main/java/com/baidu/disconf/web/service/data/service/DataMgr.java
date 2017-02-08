package com.baidu.disconf.web.service.data.service;

import java.util.List;

import com.baidu.disconf.web.service.data.bo.Data;

public interface DataMgr {

	List<Data> getDataList();

	Data getData();
}
