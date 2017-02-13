package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestHttp {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			TestHttp http=new TestHttp("http://192.168.10.135");
			http.session();
			http.login("admin", "admin");
			http.session();
			http.applist();
			http.signout();
			System.out.println("----------------------------------------");
		}
	}

	protected String domain;
	private CookieStore cookieStore;
	public CloseableHttpClient httpClient;

	public TestHttp(String domain) {
		this.domain = domain;
		cookieStore = new BasicCookieStore();
		httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
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

			if (res.contains("true")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
		//
	}

	public boolean session() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/account/session");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nsession:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void signout() {
		if (session()) {
			try {
				HttpGet httpGet = new HttpGet(domain + "/api/account/signout");

				CloseableHttpResponse response = httpClient.execute(httpGet);

				HttpEntity responseEntity = response.getEntity();

				String res = EntityUtils.toString(responseEntity, "UTF-8");

				System.out.println("\nsignout:\n\t" + res);

				EntityUtils.consume(responseEntity);

				response.close();

			} catch (Exception e) {

			}
		}

	}

	public boolean applist() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/app/list");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nlist:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void close() {
		try {
			signout();

			httpClient.close();
		} catch (Exception e) {

		}
	}

}
