package com.baidu.disconf.web.service.area.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;

@Table(db = DB.DB_NAME, name = "area", keyColumn = Columns.AREA_ID)
public class Area extends BaseObject<Long> {

	private static final long serialVersionUID = 1L;
	protected static final Logger LOG = LoggerFactory.getLogger(Area.class);

	@Column(value = Columns.DESC)
	private String desc;

	@Column(value = Columns.HOSTPORT)
	private String hostport;

	@Column(value = Columns.NAME)
	private String name;

	@Column(value = Columns.PASSWORD)
	private String password;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getHostport() {
		return hostport;
	}

	public void setHostport(String hostport) {
		this.hostport = hostport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Area [desc=" + desc + ", hostport=" + hostport + ", name=" + name + ", password=" + password + "]";
	}

	

}
