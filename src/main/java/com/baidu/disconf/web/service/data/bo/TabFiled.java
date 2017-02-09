package com.baidu.disconf.web.service.data.bo;

import java.io.Serializable;

public class TabFiled implements Serializable {
	private static final long serialVersionUID = 1L;
	private String columnName;
	private String columnClassName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	@Override
	public String toString() {
		return "TabFiled [columnName=" + columnName + ", columnClassName=" + columnClassName + "]";
	}

}
