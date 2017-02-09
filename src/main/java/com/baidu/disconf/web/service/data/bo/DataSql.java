package com.baidu.disconf.web.service.data.bo;

import java.io.Serializable;

public class DataSql implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insertSql;
	private String updateSql;

	public String getInsertSql() {
		return insertSql;
	}

	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	@Override
	public String toString() {
		return "DataSql [insertSql=" + insertSql + ", updateSql=" + updateSql + "]";
	}

}