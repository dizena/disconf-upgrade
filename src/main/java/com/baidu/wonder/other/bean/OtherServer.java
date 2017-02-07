package com.baidu.wonder.other.bean;

public class OtherServer {
	private String host;
	private String name;
	private String passwd;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "OtherServer [host=" + host + ", name=" + name + ", passwd=" + passwd + "]";
	}

}
