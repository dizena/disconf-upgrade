package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;

public class TestDataSync {

	protected String domain;
	public CloseableHttpClient httpClient = HttpClients.custom().build();

	public static void main(String[] args) throws IOException {
		try {
			
			TestDataSync t=new TestDataSync();
			
			t.domain = "http://127.0.0.1:56789";
			
			boolean b= t.login("admin", "admin");
			
			if(b){

				List<DataSql>  datas =t.db2Api();
				
				System.out.println(datas);
				
				t.api2Db(datas);
			}
			
			t.httpClient.close();
			
		} catch (Exception e) {
			e.printStackTrace();
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
		String res = null;
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

			res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\n" + domain + " api2Db:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();
			bos.close();
			instream.close();

			httpPost.releaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
			if (res.contains("true")) {
				return true;
			} else {
				return false;
			}
		}

		if (res.contains("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean login(String name, String passwd) {
		try {

			HttpPost httpPost = new HttpPost(domain + "/api/account/signin");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", name));
			nvps.add(new BasicNameValuePair("password", passwd));
			nvps.add(new BasicNameValuePair("remember", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nlogin:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
		//
	}

	public static void errTest() {
		DisconfRemoteBizDataApi t = new DisconfRemoteBizDataApi("http://127.0.0.1:56789");

		if (t.session() || t.login("admin", "admin")) {

			Data d = t.getData();

			List<DataSql> datas = t.db2Api();

			// t.api2Db(datas);

			t.close();

			System.out.println("data:" + d + ",datas:" + datas.size());

		} else {
			System.out.println("系统有问题");
		}

		// end
	}

}
