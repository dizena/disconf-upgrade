package com.baidu.disconf.web.service.data.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.bo.DataSync;
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
	public int exec(final String sql) {
		return jdbcTemplate.execute(new StatementCallback<Integer>() {
			@Override
			public Integer doInStatement(Statement stmt) throws SQLException, DataAccessException {
				return stmt.executeUpdate(sql);
			}
		});
	}

	@Override
	public List<String> getTabs() {
		return DataUtil.getTabs(jdbcTemplate);
	}

	@Override
	public List<DataSql> getDatas(String tab) {
		List<TabFiled> filedNameAndClassList =DataUtil.getFileds(jdbcTemplate, tab);
		List<DataSql> list =DataUtil.getDatas(jdbcTemplate, tab, filedNameAndClassList);
		return list;
	}

	@Override
	public DataSync getDataSync(String tab) {
		List<TabFiled> filedNameAndClassList =DataUtil.getFileds(jdbcTemplate, tab);
		return DataUtil.getDataSync(jdbcTemplate, tab, filedNameAndClassList);
	}


}
