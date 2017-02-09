package com.baidu.disconf.web.service.data.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.bo.TabFiled;
import com.baidu.disconf.web.service.data.dao.DataDao;
import com.baidu.disconf.web.service.data.utils.DataUtil;

@Service
public class DataDaoImpl implements DataDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "onedbJdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int exec(String sql) {
		int i = jdbcTemplate.update(sql);
		return i;
	}

	@Override
	public List<String> getTabs() {
		try {
			return DataUtil.getTabs(jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DataSql> getDatas(String tab) {
		try {
			List<TabFiled> filedNameAndClassList =DataUtil.getFileds(jdbcTemplate.getDataSource().getConnection(), tab);
			List<DataSql> list =DataUtil.getDatas(jdbcTemplate.getDataSource().getConnection(), tab, filedNameAndClassList);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
