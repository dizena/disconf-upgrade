package com.baidu.wonder.other;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizAppApi extends DisconfRemoteBaseApi {

	public DisconfRemoteBizAppApi(String domain) {
		super(domain);
	}

	public boolean addapp(AppNewForm appNewForm) {
		try {
			String url=domain + "/api/app/add";
			
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("app", appNewForm.getApp()));
			nvps.add(new BasicNameValuePair("desc", appNewForm.getDesc()));
			nvps.add(new BasicNameValuePair("emails", appNewForm.getEmails()));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " addapp:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();
			
			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			}else{
				log.error("error sync "+url+" with data "+JsonUtils.toJson(appNewForm));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean delapp(Long appid){
		try {
			String url=domain + "/api/app/delete?id=" + appid;
			
			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " delapp:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();
			
			httpGet.releaseConnection();

			if (res.contains("true")) {
				return true;
			}else{
				log.error("error sync "+url+" with data "+JsonUtils.toJson(appid));
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
