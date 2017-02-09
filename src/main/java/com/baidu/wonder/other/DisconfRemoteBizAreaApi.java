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

import com.baidu.disconf.web.service.area.bo.Area;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizAreaApi extends DisconfRemoteBaseApi {

	public DisconfRemoteBizAreaApi(String domain) {
		super(domain);
	}

	public boolean addArea(Area area) {
		try {
			String url = domain + "/api/area/add";

			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("hostport", area.getHostport()));
			nvps.add(new BasicNameValuePair("name", area.getName()));
			nvps.add(new BasicNameValuePair("password", area.getPassword()));
			nvps.add(new BasicNameValuePair("desc", area.getDesc()));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " addapp:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(area));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean delArea(Long appid) {
		try {

			String url = domain + "/api/area/delete?id="+ appid;

			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " delapp:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(appid));
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
