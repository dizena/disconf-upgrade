package com.baidu.wonder.other;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

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
			nvps.add(new BasicNameValuePair("value", new String(confNewForm.getValue().getBytes("UTF-8"))));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

			String res =execute(httpPost);

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

			httpPut.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

			String res =execute(httpPut);

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
	
	public boolean updateFile(ConfNewForm confNewForm, byte[] bs,String name) {
		String url = domain + "/api/web/config/file";

		HttpPost httpPost = new HttpPost(url);
		
		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.addBinaryBody("myfilerar",bs,ContentType.MULTIPART_FORM_DATA,name)
				.addTextBody("appId", confNewForm.getAppId() + "")
				.addTextBody("version", confNewForm.getVersion())
				.addTextBody("envId", confNewForm.getEnvId() + "")
				.build();

		httpPost.setEntity(httpEntity);

		String res = execute(httpPost);

		if (res.contains("true")) {
			return true;
		} else {
			log.error("error sync " + url + " with data " + JsonUtils.toJson(confNewForm)+" \n"+new String(bs));
		}

		return false;
	}

	public boolean updateFile(long configId, byte[] bs,String name) {
		String url = domain + "/api/web/config/file/" + configId;

		HttpPost httpPost = new HttpPost(url);


		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.addBinaryBody("myfilerar",bs,ContentType.MULTIPART_FORM_DATA,name)
				.build();

		httpPost.setEntity(httpEntity);

		String res = execute(httpPost);

		if (res.contains("true")) {
			return true;
		} else {
			log.error("error sync " + url + " with data " + JsonUtils.toJson(configId));
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

			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
			String res =execute(httpPost);
			
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

	public boolean updateFileWithText(long configId, String fileContent) {
		try {
			String url = domain + "/api/web/config/filetext/" + configId;

			HttpPut httpPut = new HttpPut(url);
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("fileContent", fileContent));

			httpPut.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

			String res = execute(httpPut);

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

			String res = execute(http);

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

			String res = execute(http);

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

			String res = execute(http);

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
