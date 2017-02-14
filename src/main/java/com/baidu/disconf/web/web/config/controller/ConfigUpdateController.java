package com.baidu.disconf.web.web.config.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.disconf.web.service.config.bo.Config;
import com.baidu.disconf.web.service.config.service.ConfigMgr;
import com.baidu.disconf.web.service.sync.service.SyncMgr;
import com.baidu.disconf.web.utils.ThreadPools;
import com.baidu.disconf.web.web.config.validator.ConfigValidator;
import com.baidu.disconf.web.web.config.validator.FileUploadValidator;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.exception.FileUploadException;
import com.baidu.dsp.common.vo.JsonObjectBase;

/**
 * 专用于配置更新、删除
 *
 * @author liaoqiqi
 * @version 2014-6-24
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/web/config")
public class ConfigUpdateController extends BaseController {

	@Autowired
	private ConfigMgr configMgr;

	@Autowired
	private ConfigValidator configValidator;

	@Autowired
	private FileUploadValidator fileUploadValidator;

	@Autowired
	private SyncMgr syncMgr;

	/**
	 * 配置项的更新
	 *
	 * @param configId
	 * @param value
	 *
	 * @return
	 */
	@RequestMapping(value = "/item/{configId}", method = RequestMethod.PUT)
	@ResponseBody
	public JsonObjectBase updateItem(@PathVariable long configId, String value) {

		// 业务校验
		configValidator.validateUpdateItem(configId, value);

		LOG.info("start to update config: " + configId);

		//
		// 更新, 并写入数据库
		//
		String emailNotification = "";
		emailNotification = configMgr.updateItemValue(configId, value);

		//
		// 通知ZK
		//
		configMgr.notifyZookeeper(configId);

		// HTTP联动操作
		if (getSysc()) {
			final  long configIdT=configId;
			final String valueT=value;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.updateItemSync(configIdT, valueT);
					LOG.info("not sync updateItem " + i);
				}
			});
		}

		return buildSuccess(emailNotification);
	}

	/**
	 * 配置文件的更新
	 *
	 * @param configId
	 * @param file
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/file/{configId}", method = RequestMethod.POST)
	public JsonObjectBase updateFile(@PathVariable long configId, @RequestParam("myfilerar") MultipartFile file) {

		//
		// 校验
		//
		int fileSize = 1024 * 1024 * 4;
		String[] allowExtName = { ".properties", ".xml" };
		fileUploadValidator.validateFile(file, fileSize, allowExtName);

		// 业务校验
		configValidator.validateUpdateFile(configId, file.getOriginalFilename());

		//
		// 更新
		//
		String emailNotification = "";
		try {

			String str = new String(file.getBytes(), "UTF-8");
			LOG.info("receive file: " + str);

			emailNotification = configMgr.updateItemValue(configId, str);
			LOG.info("update " + configId + " ok");

		} catch (Exception e) {

			LOG.error(e.toString());
			throw new FileUploadException("upload file error", e);
		}

		//
		// 通知ZK
		//
		configMgr.notifyZookeeper(configId);

		// HTTP联动操作
		if (getSysc()) {
			final long configIdT=configId;
			final MultipartFile fileT=file;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.updateFileSync(configIdT, fileT);
					LOG.info("not sync updatefile " + i);
				}
			});
		}

		return buildSuccess(emailNotification);
	}

	/**
	 * 配置文件的更新(文本修改)
	 *
	 * @param configId
	 * @param fileContent
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/filetext/{configId}", method = RequestMethod.PUT)
	public JsonObjectBase updateFileWithText(@PathVariable long configId, @NotNull String fileContent) {

		//
		// 更新
		//
		String emailNotification = "";
		try {

			String str = new String(fileContent.getBytes(), "UTF-8");
			LOG.info("receive file: " + str);

			emailNotification = configMgr.updateItemValue(configId, str);
			LOG.info("update " + configId + " ok");

		} catch (Exception e) {

			throw new FileUploadException("upload.file.error", e);
		}

		//
		// 通知ZK
		//
		configMgr.notifyZookeeper(configId);

		// HTTP联动操作
		if (getSysc()) {
			final long configIdT=configId;
			final String fileContentT=fileContent;
			ThreadPools.execute(new Runnable() {
				
				@Override
				public void run() {
					int i = syncMgr.updateFileWithTextSync(configIdT, fileContentT);
					LOG.info("not sync updatefileWithText " + i);
				}
			});
		}

		return buildSuccess(emailNotification);
	}

	/**
	 * delete
	 *
	 * @return
	 */
	@RequestMapping(value = "/{configId}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonObjectBase delete(@PathVariable long configId) {

		configValidator.validateDelete(configId);

		configMgr.delete(configId);

		// HTTP联动操作
		if (getSysc()) {
			final long configIdT=configId;
			ThreadPools.execute(new Runnable() {
				
				@Override
				public void run() {
					int i = syncMgr.deleteConfigSync(configIdT);
					LOG.info("not sync deleteConfigSync " + i);
				}
			});
		}	

		return buildSuccess("删除成功");
	}

	/**
	 * notifyOne
	 *
	 * @return
	 */
	@RequestMapping(value = "/notifyOne", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase notifyOne(@RequestParam("id") Long configId) {
		configMgr.notifyZookeeper(configId);

		// HTTP联动操作
		if (getSysc()) {
			final Long configIdT=configId;
			ThreadPools.execute(new Runnable() {
				
				@Override
				public void run() {
					int i = syncMgr.notifyOneSync(configIdT);
					LOG.info("not sync notifyOneSync " + i);
				}
			});
		}

		return buildSuccess("通知成功");
	}

	/**
	 * notifyAll
	 *
	 * @return
	 */
	@RequestMapping(value = "/notifySome", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase notifySome() {
		List<Config> cfgs = configMgr.getAllNormal();
		if (cfgs != null && cfgs.size() > 0) {
			for (Config config : cfgs) {
				configMgr.notifyZookeeper(config.getId());
			}
		}

		// HTTP联动操作
		if (getSysc()) {
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.notifySomeSync();
					LOG.info("not sync notifySomeSync " + i);
				}
			});
		}

		return buildSuccess("通知成功");
	}

}
