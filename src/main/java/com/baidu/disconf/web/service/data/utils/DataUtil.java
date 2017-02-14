package com.baidu.disconf.web.service.data.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.bo.TabFiled;

public class DataUtil {

	public static List<String> getTabs(JdbcTemplate jdbcTemplate) {
		String sql = "show tables";
		
		List<String> tabs = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet res, int i) throws SQLException {
				return res.getString(1);
			}

		});

		return tabs;
	}

	public static List<TabFiled> getFileds(JdbcTemplate jdbcTemplate, String tab) {
		try{

			String sql = "SELECT * FROM " + tab + " LIMIT 1";
			
			List<ResultSetMetaData> ress=jdbcTemplate.query(sql, new RowMapper<ResultSetMetaData>(){
				@Override
				public ResultSetMetaData mapRow(ResultSet res, int k) throws SQLException {
					return res.getMetaData();
				}
				
			});
			
			if(ress!=null&&ress.size()>0){
				ResultSetMetaData data=ress.get(0);
				List<TabFiled> list = new ArrayList<TabFiled>();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String columnName = data.getColumnName(i);
					String columnClassName = data.getColumnClassName(i);
					TabFiled filed = new TabFiled();
					filed.setColumnName(columnName);
					filed.setColumnClassName(columnClassName);
					list.add(filed);
				}
				return list;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public static List<DataSql> getDatas(JdbcTemplate jdbcTemplate, String tab, final List<TabFiled> filedNameAndClassList) {
		String dataQuerySql = "SELECT ";
		String dataInsertSql = "INSERT INTO " + tab + "(";
		String dataUpdateSql = "UPDATE " + tab + " SET ";

		for (TabFiled filed : filedNameAndClassList) {
			dataQuerySql += filed.getColumnName() + ",";
			dataInsertSql += filed.getColumnName() + ",";
		}
		dataQuerySql = dataQuerySql.substring(0, dataQuerySql.length() - 1);
		dataInsertSql = dataInsertSql.substring(0, dataInsertSql.length() - 1);
		dataQuerySql += " FROM " + tab;
		dataInsertSql += ") VALUES (";
		
		
		final String dataInsertSqlF = dataInsertSql;
		final String dataUpdateSqlF = dataUpdateSql;
		
		List<DataSql> list = jdbcTemplate.query(dataQuerySql, new RowMapper<DataSql>(){

			@Override
			public DataSql mapRow(ResultSet res, int i) throws SQLException {
				String dataInsertSqlDo = dataInsertSqlF;
				String dataUpdateSqlDo = dataUpdateSqlF;
				
				int index = 1;

				// 一行数据取出
				for (TabFiled filed : filedNameAndClassList) {
					String ClassName = filed.getColumnClassName();
					String ColumnName = filed.getColumnName();
					dataUpdateSqlDo += ColumnName + "=";
					if (ClassName.contains("String")) {
						dataInsertSqlDo += "'" + res.getString(index) + "',";
						dataUpdateSqlDo += "'" + res.getString(index) + "',";
					}
					if (ClassName.contains("Long")) {
						dataInsertSqlDo += res.getLong(index) + ",";
						dataUpdateSqlDo += res.getLong(index) + ",";
					}
					if (ClassName.contains("Integer")) {
						dataInsertSqlDo += res.getInt(index) + ",";
						dataUpdateSqlDo += res.getLong(index) + ",";
					}
					if (ClassName.contains("Date")) {
						dataInsertSqlDo += "'" + res.getDate(index) + "',";
						dataUpdateSqlDo += res.getLong(index) + ",";
					}
					index++;
				}

				dataInsertSqlDo = dataInsertSqlDo.substring(0, dataInsertSqlDo.length() - 1);
				dataUpdateSqlDo = dataUpdateSqlDo.substring(0, dataUpdateSqlDo.length() - 1);
				dataInsertSqlDo += ")";

				TabFiled filed0 = filedNameAndClassList.get(0);
				if (filed0.getColumnClassName().contains("String")) {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + res.getString(1);
				} else if (filed0.getColumnClassName().contains("Long")) {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + res.getLong(1);
				} else {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + res.getInt(1);
				}
				
				DataSql sqlObj = new DataSql();
				sqlObj.setInsertSql(dataInsertSqlDo);
				sqlObj.setUpdateSql(dataUpdateSqlDo);
				
				return sqlObj;
			}
			
		});

		return list;
	}
}
