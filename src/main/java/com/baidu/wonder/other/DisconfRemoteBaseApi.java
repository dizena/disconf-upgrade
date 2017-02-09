package com.baidu.wonder.other;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DisconfRemoteBaseApi {
	public static Logger log = LoggerFactory.getLogger(DisconfRemoteBaseApi.class);

	private BasicCookieStore cookieStore;
	public CloseableHttpClient httpClient;
	protected String domain;

	public DisconfRemoteBaseApi(String domain, BasicCookieStore cookieStore) {
		this.domain = domain;
		this.cookieStore = cookieStore;

		httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies.isEmpty()) {
			log.info("None");
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				log.info("- " + cookies.get(i).toString());
			}
		}

	}

	public boolean login(String name, String passwd) {
		//
		try {
			boolean b =session();
			if(b){
				return b;
			}
			
			HttpPost httpPost = new HttpPost(domain + "/api/account/signin");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", name));
			nvps.add(new BasicNameValuePair("password", passwd));
			nvps.add(new BasicNameValuePair("remember", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\nlogin:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;
		//
	}

	public boolean session() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/account/session");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\nsession:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public void signout() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/account/signout");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\nsignout:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

		} catch (Exception e) {

		}
	}

	public void close() {
		try {
			signout();

			cookieStore.clear();

			httpClient.close();
		} catch (Exception e) {

		}
	}
}
