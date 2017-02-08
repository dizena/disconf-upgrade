package com.baidu.wonder.other;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.data.bo.Data;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizDataApi extends DisconfRemoteBaseApi {

	public static void main(String[] args) throws IOException {
		DisconfRemoteBizDataApi t = new DisconfRemoteBizDataApi("http://127.0.0.1:56789");
		boolean b = t.login("admin", "admin");
		System.out.println(b);
		Data d =t.getData();
		System.out.println(d);
		t.close();
	}

	public DisconfRemoteBizDataApi(String domain) {
		super(domain);
	}

	public Data getData() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/data/getCount");

			response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " getData:\n\t" + res);

			EntityUtils.consume(responseEntity);

			Data d = (Data) JsonUtils.json2Object(res, Data.class);

			response.close();

			return d;
		} catch (Exception e) {

		}
		return null;
	}

}
