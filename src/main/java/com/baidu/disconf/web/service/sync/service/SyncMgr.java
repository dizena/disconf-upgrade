package com.baidu.disconf.web.service.sync.service;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;

public interface SyncMgr {

	int addAppSync(AppNewForm appNewForm);

	int delAppSync(Long appid);

	int addAreaSync(Area area);

	int delAreaSync(Long id);

	int addItemSync(ConfNewItemForm confNewForm);

	int updateFileSync(ConfNewForm confNewForm, byte[] bs,String name);

	int updateFileWithTextSync(ConfNewForm confNewForm, String fileContent, String fileName);

	int updateItemSync(long configId, String value);

	int updateFileSync(long configId, byte[] bs,String name);

	int updateFileWithTextSync(long configId, String fileContent);

	int deleteConfigSync(long configId);

	int notifyOneSync(Long configId);

	int notifySomeSync();
	
}
