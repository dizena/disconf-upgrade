package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class FileUp {
	protected String domain;
	public CloseableHttpClient httpClient = HttpClients.custom().build();

	public static void main(String[] args) {

		try {

			FileUp t = new FileUp();

			t.domain = "http://192.168.10.130";

			t.login("admin", "admin");

			File file = new File("E:\\Desktop\\c.properties");

			t.updateFile(file);

			t.httpClient.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean updateFile(File file) {
		try {
			String url = domain + "/api/web/config/file";

			HttpPost httpPost = new HttpPost(url);

			HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("myfilerar", new FileBody(file))
					.addTextBody("appId", "1").addTextBody("version", "1.0").addTextBody("envId", "1").build();

			httpPost.setEntity(httpEntity);

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\n" + domain + " updateFile:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				System.out.println("error sync " + url + " with data ");
			}
		} catch (Exception e) {
			return false;
		}

		return false;
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

}
