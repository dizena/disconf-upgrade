package com.baidu.disconf.web.service.data.utils;

import java.sql.*;
import java.util.*;

import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.bo.TabFiled;

public class DataUtil {
	
	public static List<String> getTabs(Connection conn) {
		try {
			String sql = "show tables";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<String> tabs = new ArrayList<>();
			while (rs.next()) {
				tabs.add(rs.getString(1));
			}
			return tabs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<TabFiled> getFileds(Connection conn, String tab) {
		try {
			// 得到表的字段结构
			String querySql = "SELECT * FROM " + tab + " LIMIT 1";
			PreparedStatement ps = conn.prepareStatement(querySql);
			ResultSet res = ps.executeQuery();
			ResultSetMetaData data = res.getMetaData();

			List<TabFiled> list = new ArrayList<>();

			for (int i = 1; i <= data.getColumnCount(); i++) {
				String columnName = data.getColumnName(i);
				String columnClassName = data.getColumnClassName(i);
				TabFiled filed = new TabFiled();
				filed.setColumnName(columnName);
				filed.setColumnClassName(columnClassName);
				list.add(filed);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<DataSql> getDatas(Connection conn, String tab, List<TabFiled> filedNameAndClassList) {
		try {
			List<DataSql> list = new ArrayList<>();

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

			// 查询数据
			PreparedStatement pst = conn.prepareStatement(dataQuerySql);
			ResultSet rest = pst.executeQuery();

			// 取数据
			while (rest.next()) {
				String dataInsertSqlDo = dataInsertSql;
				String dataUpdateSqlDo = dataUpdateSql;
				int index = 1;

				// 一行数据取出
				for (TabFiled filed : filedNameAndClassList) {
					String ClassName = filed.getColumnClassName();
					String ColumnName = filed.getColumnName();
					dataUpdateSqlDo += ColumnName + "=";
					if (ClassName.contains("String")) {
						dataInsertSqlDo += "'" + rest.getString(index) + "',";
						dataUpdateSqlDo += "'" + rest.getString(index) + "',";
					}
					if (ClassName.contains("Long")) {
						dataInsertSqlDo += rest.getLong(index) + ",";
						dataUpdateSqlDo += rest.getLong(index) + ",";
					}
					if (ClassName.contains("Integer")) {
						dataInsertSqlDo += rest.getInt(index) + ",";
						dataUpdateSqlDo += rest.getLong(index) + ",";
					}
					if (ClassName.contains("Date")) {
						dataInsertSqlDo += "'" + rest.getDate(index) + "',";
						dataUpdateSqlDo += rest.getLong(index) + ",";
					}
					index++;
				}

				dataInsertSqlDo = dataInsertSqlDo.substring(0, dataInsertSqlDo.length() - 1);
				dataUpdateSqlDo = dataUpdateSqlDo.substring(0, dataUpdateSqlDo.length() - 1);
				dataInsertSqlDo += ")";

				TabFiled filed0 = filedNameAndClassList.get(0);
				if (filed0.getColumnClassName().contains("String")) {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + rest.getString(1);
				} else if (filed0.getColumnClassName().contains("Long")) {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + rest.getLong(1);
				} else {
					dataUpdateSqlDo += " WHERE " + filed0.getColumnName() + "=" + rest.getInt(1);
				}

				DataSql sqlObj = new DataSql();
				sqlObj.setInsertSql(dataInsertSqlDo);
				sqlObj.setUpdateSql(dataUpdateSqlDo);

				list.add(sqlObj);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
