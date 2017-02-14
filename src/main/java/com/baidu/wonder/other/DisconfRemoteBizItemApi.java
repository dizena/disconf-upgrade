package com.baidu.wonder.other;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;
import com.github.knightliao.apollo.utils.data.JsonUtils;

public class DisconfRemoteBizItemApi extends DisconfRemoteBaseApi {

	public DisconfRemoteBizItemApi(String domain) {
		super(domain);
	}

	public boolean addItem(ConfNewItemForm confNewForm) {
		try {
			String url = domain + "/api/web/config/item";

			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("appId", confNewForm.getAppId() + ""));
			nvps.add(new BasicNameValuePair("version", confNewForm.getVersion()));
			nvps.add(new BasicNameValuePair("key", confNewForm.getKey()));
			nvps.add(new BasicNameValuePair("envId", confNewForm.getEnvId() + ""));
			nvps.add(new BasicNameValuePair("value", confNewForm.getValue()));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " addItem:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(confNewForm));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean updateFile(ConfNewForm confNewForm, MultipartFile file) {
		try {
			String url = domain + "/api/web/config/file";

			HttpPost httpPost = new HttpPost(url);
			
			File f=new File(file.getName());
			
			file.transferTo(f);

			HttpEntity httpEntity = MultipartEntityBuilder.create()
					.addPart("myfilerar", new FileBody(f))
					.addTextBody("appId", confNewForm.getAppId() + "")
					.addTextBody("version", confNewForm.getVersion())
					.addTextBody("envId", confNewForm.getEnvId() + "")
					.build();

			httpPost.setEntity(httpEntity);

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " updateFile:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(confNewForm));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean updateFileWithText(ConfNewForm confNewForm, String fileContent, String fileName) {
		try {
			String url = domain + "/api/web/config/filetext";

			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("appId", confNewForm.getAppId() + ""));
			nvps.add(new BasicNameValuePair("version", confNewForm.getVersion()));
			nvps.add(new BasicNameValuePair("fileContent", fileContent));
			nvps.add(new BasicNameValuePair("envId", confNewForm.getEnvId() + ""));
			nvps.add(new BasicNameValuePair("fileName", fileName));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " updateFileWithText:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(confNewForm));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean updateItem(long configId, String value) {
		try {
			String url = domain + "/api/web/config/item/" + configId;

			HttpPut httpPut = new HttpPut(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("value", value));

			httpPut.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPut);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " updateItem:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPut.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(configId + "," + value));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean updateFile(long configId, MultipartFile file) {
		try {
			String url = domain + "/api/web/config/file/" + configId;

			HttpPost httpPost = new HttpPost(url);

			File f=new File(file.getName());
			
			file.transferTo(f);

			HttpEntity httpEntity = MultipartEntityBuilder.create()
					.addPart("myfilerar", new FileBody(f))
					.build();

			httpPost.setEntity(httpEntity);

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " updateFile:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPost.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(configId));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean updateFileWithText(long configId, String fileContent) {
		try {
			String url = domain + "/api/web/config/filetext/" + configId;

			HttpPut httpPut = new HttpPut(url);
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("fileContent", fileContent));

			httpPut.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPut);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " updateFileWithText:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			httpPut.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(configId + "," + fileContent));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean deleteConfig(long configId) {
		try {
			String url = domain + "/api/web/config/" + configId;

			HttpDelete http = new HttpDelete(url);

			CloseableHttpResponse response = httpClient.execute(http);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " deleteConfig:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			http.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(configId));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean notifyOne(Long configId) {
		try {
			String url = domain + "/api/web/config/notifyOne?id=" + configId;

			HttpGet http = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(http);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " notifyOne:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			http.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " with data " + JsonUtils.toJson(configId));
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public boolean notifySome() {
		try {
			String url = domain + "/api/web/config/notifySome";

			HttpGet http = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(http);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			log.info("\n" + domain + " notifySome:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			http.releaseConnection();

			if (res.contains("true")) {
				return true;
			} else {
				log.error("error sync " + url + " ");
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

}
