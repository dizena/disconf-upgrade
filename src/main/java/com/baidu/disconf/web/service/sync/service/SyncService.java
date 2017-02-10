package com.baidu.disconf.web.service.sync.service;

import org.springframework.web.multipart.MultipartFile;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;

public interface SyncService {

	int addApp(AppNewForm appNewForm);

	int delApp(Long appid);

	int addArea(Area area);

	int delArea(Long id);

	int addItem(ConfNewItemForm confNewForm);

	int updateFile(ConfNewForm confNewForm, MultipartFile file);

	int updateFileWithText(ConfNewForm confNewForm, String fileContent, String fileName);

	int updateItem(long configId, String value);

	int updateFile(long configId, MultipartFile file);

	int updateFileWithText(long configId, String fileContent);

	int deleteConfig(long configId);

	int notifyOne(Long configId);

	int notifySome();
	
}
