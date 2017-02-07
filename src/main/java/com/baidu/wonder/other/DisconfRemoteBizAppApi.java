package com.baidu.wonder.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.app.form.AppNewForm;

public class DisconfRemoteBizAppApi extends DisconfRemoteBaseApi{

	public DisconfRemoteBizAppApi(String domain) {
		super(domain);
	}
	
	public void addapp(AppNewForm appNewForm) throws IOException {
		//
		HttpPost httpPost = new HttpPost(domain + "/api/app");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("app", appNewForm.getApp()));
		nvps.add(new BasicNameValuePair("desc", appNewForm.getDesc()));
		nvps.add(new BasicNameValuePair("emails", appNewForm.getEmails()));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		response = httpClient.execute(httpPost);

		HttpEntity responseEntity = response.getEntity();

		String res = EntityUtils.toString(responseEntity, "UTF-8");

		log.info("\n"+domain+" addapp:\n\t" + res);

		EntityUtils.consume(responseEntity);

		response.close();

		//
	}
	
	public void delapp(Long appid) throws IOException {
		//
		HttpGet httpGet = new HttpGet(domain + "/api/app/delete?id="+appid);

		response = httpClient.execute(httpGet);

		HttpEntity responseEntity = response.getEntity();

		String res = EntityUtils.toString(responseEntity, "UTF-8");

		log.info("\n"+domain+" delapp:\n\t" + res);
		
		EntityUtils.consume(responseEntity);

		response.close();

		//
	}

}
