package com.baidu.wonder.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DisconfRemoteBaseApi {
	public static Logger log = LoggerFactory.getLogger(DisconfRemoteBaseApi.class);

	public CloseableHttpClient httpClient;
	protected String domain;

	public DisconfRemoteBaseApi(String domain) {
		this.domain = domain;
		httpClient = HttpClients.custom().build();
	}

	public boolean login(String name, String passwd) {
		try {

			HttpPost httpPost = new HttpPost(domain + "/api/account/signin");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", name));
			nvps.add(new BasicNameValuePair("password", passwd));
			nvps.add(new BasicNameValuePair("remember", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

			String res = execute(httpPost);

			if (res.contains("true")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
		//
	}

	public boolean session() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/account/session");

			String res = execute(httpGet);

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void signout() {
		if (session()) {
			HttpGet httpGet = new HttpGet(domain + "/api/account/signout");
			execute(httpGet);
		}
	}

	public String execute(HttpRequestBase httpRequest) {
		CloseableHttpResponse response = null;
		try {
			//httpRequest.setHeader("Accept", "*/*"); 
			//httpRequest.setHeader("Accept-Encoding", "gzip, deflate"); 
			//httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			//httpRequest.setHeader("Connection", "keep-alive");
			//httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			//httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");    
			
			response = httpClient.execute(httpRequest);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			EntityUtils.consume(responseEntity);
			
			log.info("execute res="+res);

			return res;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpRequest.releaseConnection();
				
				if (response != null)
					response.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	public void close() {
		try {
			signout();
			httpClient.close();
		} catch (Exception e) {

		}
	}
}
