package com.baidu.disconf.web.service.data.bo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Data implements Serializable {

	protected static final Logger LOG = LoggerFactory.getLogger(Data.class);
	private static final long serialVersionUID = -2217832889126331664L;

	private Long areaId;
	private String hostport;
	private int areaCount;
	private int appCount;
	private int envCount;
	private int cfgCount;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getHostport() {
		return hostport;
	}

	public void setHostport(String hostport) {
		this.hostport = hostport;
	}

	public int getAreaCount() {
		return areaCount;
	}

	public void setAreaCount(int areaCount) {
		this.areaCount = areaCount;
	}

	public int getAppCount() {
		return appCount;
	}

	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}

	public int getEnvCount() {
		return envCount;
	}

	public void setEnvCount(int envCount) {
		this.envCount = envCount;
	}

	public int getCfgCount() {
		return cfgCount;
	}

	public void setCfgCount(int cfgCount) {
		this.cfgCount = cfgCount;
	}

	@Override
	public String toString() {
		return "Data [areaId=" + areaId + ", hostport=" + hostport + ", areaCount=" + areaCount + ", appCount="
				+ appCount + ", envCount=" + envCount + ", cfgCount=" + cfgCount + "]";
	}

}
