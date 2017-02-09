package com.baidu.wonder.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizDataApi extends DisconfRemoteBaseApi {



	public static void main(String[] args) throws IOException {
		BasicCookieStore cookieStore=new BasicCookieStore();
		DisconfRemoteBizDataApi t = new DisconfRemoteBizDataApi("http://127.0.0.1:56789",cookieStore);
		boolean b = t.login("admin", "admin");
		Data d = t.getData();

		List<DataSql> datas = t.db2Api();
		
		t.api2Db(datas);
		
		cookieStore.clear();
		
		t.close();
		
		System.out.println("login:" + b + ",data:" + d + ",datas:" + datas.size());
		//login:true,data:Data [areaId=1, hostport=http://127.0.0.1:56789, areaCount=2, appCount=2, envCount=4, cfgCount=3],datas:113
	}

	public DisconfRemoteBizDataApi(String domain, BasicCookieStore cookieStore) {
		super(domain, cookieStore);
	}

	public Data getData() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/data/getCount");

			CloseableHttpResponse response = httpClient.execute(httpGet);

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

	@SuppressWarnings("unchecked")
	public List<DataSql> db2Api() {
		try {
			// 请求
			HttpGet httpGet = new HttpGet(domain + "/api/data/db2Api");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			InputStream ins = responseEntity.getContent();

			ObjectInputStream objins = new ObjectInputStream(ins);

			List<DataSql> datas = (List<DataSql>) objins.readObject();

			response.close();

			return datas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void api2Db(List<DataSql> datas) {
		try {

			// 转发
			HttpPost httpPost = new HttpPost(domain + "/api/data/api2Db");

			BasicHttpEntity entity = new BasicHttpEntity();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objout = new ObjectOutputStream(bos);
			objout.writeObject(datas);
			objout.flush();
			byte[] bs = bos.toByteArray();
			bos.flush();
			InputStream instream = new ByteArrayInputStream(bs);

			entity.setContent(instream);
			httpPost.setEntity(entity);

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " getData:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();
			bos.close();
			instream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
