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
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;
import com.baidu.wonder.other.DisconfRemoteBizItemApi;

public class TestDataSync {

	protected String domain;
	public CloseableHttpClient httpClient = HttpClients.custom().build();

	public static void main(String[] args) throws IOException {
		try {
			
			// 取数据
			List<DataSql> datas = null;
			DisconfRemoteBizDataApi api1 = new DisconfRemoteBizDataApi("http://192.168.10.130");
			if (api1.session() || api1.login("admin", "admin")) {
				datas = api1.db2Api();
				api1.close();
			}
			
			System.out.println("取数据 datas "+datas);
			System.out.println("\n");

			// 传数据
			DisconfRemoteBizDataApi api2 = new DisconfRemoteBizDataApi("http://127.0.0.1:56789");
			if (api2.session() || api2.login("admin", "admin")) {
				if (datas != null) {
					api2.api2Db(datas);
				}
				api2.close();
			}
			
			System.out.println("传数据 datas "+datas.size());
			System.out.println("\n");

			// 通知ZK
			DisconfRemoteBizItemApi api3 = new DisconfRemoteBizItemApi("http://127.0.0.1:56789");
			if (api3.session() || api3.login("admin", "admin")) {
				api3.notifySome();
				api3.close();
			}
			System.out.println("通知ZK ");
			
			
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

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objout = new ObjectOutputStream(bos);
			objout.writeObject(datas);
			objout.flush();
			byte[] bs = bos.toByteArray();
			bos.flush();
			InputStream instream = new ByteArrayInputStream(bs);

			InputStreamEntity entity=new InputStreamEntity(instream);
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
	
	public static void test() throws IOException{
		TestDataSync t=new TestDataSync();
		
		t.domain = "http://127.0.0.1:56789";
		
		boolean b= t.login("admin", "admin");
		
		if(b){

			List<DataSql>  datas =t.db2Api();
			
			System.out.println(datas);
			
			t.api2Db(datas);
		}
		
		t.httpClient.close();
	}

}
