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
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisconfRemoteBaseApi {
	public static Logger log = LoggerFactory.getLogger(DisconfRemoteBaseApi.class);

	public BasicCookieStore cookieStore = new BasicCookieStore();
	public CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	protected CloseableHttpResponse response;
	protected String domain;

	public DisconfRemoteBaseApi(String domain) {
		this.domain = domain;
	}

	
	public boolean login(String name, String passwd)  {
		//
		try{
			HttpPost httpPost = new HttpPost(domain + "/api/account/signin");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", name));
			nvps.add(new BasicNameValuePair("password", passwd));
			nvps.add(new BasicNameValuePair("remember", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\nlogin:\n\t" + res);

			EntityUtils.consume(responseEntity);

			List<Cookie> cookies = cookieStore.getCookies();
			if (cookies.isEmpty()) {
				log.info("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					log.info("- " + cookies.get(i).toString());
				}
			}
			response.close();
			
			if(res.contains("success")){
				return true;
			}
			
		}catch(Exception e){
			
		}
		return false;
		//
	}

	public void session() throws IOException {
		//
		HttpGet httpGet = new HttpGet(domain + "/api/account/session");

		response = httpClient.execute(httpGet);

		HttpEntity responseEntity = response.getEntity();

		String res = EntityUtils.toString(responseEntity, "UTF-8");

		log.info("\nsession:\n\t" + res);

		EntityUtils.consume(responseEntity);

		response.close();

		//
	}

	public void signout() throws IOException {
		//
		HttpGet httpGet = new HttpGet(domain + "/api/account/signout");

		response = httpClient.execute(httpGet);

		HttpEntity responseEntity = response.getEntity();

		String res = EntityUtils.toString(responseEntity, "UTF-8");

		log.info("\nsignout:\n\t" + res);

		EntityUtils.consume(responseEntity);

		response.close();
		//
	}

	public void close() throws IOException {
		if (response != null) {
			response.close();
		}
		httpClient.close();
	}
}
