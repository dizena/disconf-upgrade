package com.baidu.wonder.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.disconf.web.service.data.bo.DataSync;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizDataApi extends DisconfRemoteBaseApi {

	public DisconfRemoteBizDataApi(String domain) {
		super(domain);
	}

	public Data getData() {
		try {
			String url = domain + "/api/data/getCount";

			HttpGet httpGet = new HttpGet(url);

			String res = execute(httpGet);

			Data d = (Data) JsonUtils.json2Object(res, Data.class);

			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DataSql> db2Api() {
		try {
			String url = domain + "/api/data/db2Api";

			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			InputStream ins = responseEntity.getContent();

			ObjectInputStream objins = new ObjectInputStream(ins);

			List<DataSql> datas = (List<DataSql>) objins.readObject();

			response.close();

			httpGet.releaseConnection();

			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean api2Db(List<DataSql> datas) {
		try {
			// 转发
			HttpPost httpPost = new HttpPost(domain + "/api/data/api2Db");

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objout = new ObjectOutputStream(bos);
			objout.writeObject(datas);
			objout.flush();
			byte[] bs = bos.toByteArray();
			bos.flush();
			InputStream instream = new ByteArrayInputStream(bs);

			InputStreamEntity entity = new InputStreamEntity(instream);
			httpPost.setEntity(entity);
			String res = execute(httpPost);

			bos.close();
			instream.close();
			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DataSync> data2Api() {
		try {
			String url = domain + "/api/data/db2Api";

			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			InputStream ins = responseEntity.getContent();

			ObjectInputStream objins = new ObjectInputStream(ins);

			List<DataSync> datas = (List<DataSync>) objins.readObject();

			response.close();

			httpGet.releaseConnection();

			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean api2Data(List<DataSync> datas) {
		try {
			// 转发
			HttpPost httpPost = new HttpPost(domain + "/api/data/api2Db");

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objout = new ObjectOutputStream(bos);
			objout.writeObject(datas);
			objout.flush();
			byte[] bs = bos.toByteArray();
			bos.flush();
			InputStream instream = new ByteArrayInputStream(bs);

			InputStreamEntity entity = new InputStreamEntity(instream);
			
			httpPost.setEntity(entity);

			String res = execute(httpPost);

			bos.close();
			instream.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

}
