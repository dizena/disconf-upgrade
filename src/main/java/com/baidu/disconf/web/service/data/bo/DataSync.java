package com.baidu.disconf.web.service.data.bo;

import java.io.Serializable;
import java.util.List;

public class DataSync implements Serializable {
	private static final long serialVersionUID = 1L;
	private String delSql;
	private List<String> insertSqls;

	public String getDelSql() {
		return delSql;
	}

	public void setDelSql(String delSql) {
		this.delSql = delSql;
	}

	public List<String> getInsertSqls() {
		return insertSqls;
	}

	public void setInsertSqls(List<String> insertSqls) {
		this.insertSqls = insertSqls;
	}

	@Override
	public String toString() {
		return "DataSync [delSql=" + delSql + ", insertSqls=" + insertSqls + "]";
	}

}
