package com.baidu.disconf.web.service.data.dao;

import java.util.List;

import com.baidu.disconf.web.service.data.bo.DataSql;

public interface DataDao {
	int exec(String sql);

	List<String> getTabs();

	List<DataSql> getDatas(String tab);
}
