package com.baidu.disconf.web.web.config.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;
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
 * 专用于配置新建
 *
 * @author liaoqiqi
 * @version 2014-6-24
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/web/config")
public class ConfigNewController extends BaseController {

	@Autowired
	private ConfigMgr configMgr;

	@Autowired
	private ConfigValidator configValidator;

	@Autowired
	private FileUploadValidator fileUploadValidator;

	@Autowired
	private SyncMgr syncMgr;

	/**
	 * 配置项的新建
	 *
	 * @param confNewForm
	 *
	 * @return
	 */
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjectBase newItem(@Valid ConfNewItemForm confNewForm) {

		// 业务校验
		configValidator.validateNew(confNewForm, DisConfigTypeEnum.ITEM);

		//
		configMgr.newConfig(confNewForm, DisConfigTypeEnum.ITEM);

		// HTTP联动操作
		if (getSysc()) {
			final ConfNewItemForm confNewFormT =confNewForm;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.addItemSync(confNewFormT);
					LOG.info("sync add item " + i);
				}
			});
		}

		return buildSuccess("创建成功");
	}

	/**
	 * 配置文件的新建(使用上传配置文件)
	 *
	 * @param confNewForm
	 * @param file
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public JsonObjectBase updateFile(@Valid ConfNewForm confNewForm, @RequestParam("myfilerar") MultipartFile file) {

		LOG.info(confNewForm.toString());

		//
		// 校验
		//
		int fileSize = 1024 * 1024 * 4;
		String[] allowExtName = { ".properties", ".xml" };
		fileUploadValidator.validateFile(file, fileSize, allowExtName);

		//
		// 更新
		//
		String fileContent = "";
		try {

			fileContent = new String(file.getBytes(), "UTF-8");
			LOG.info("receive file: " + fileContent);

		} catch (Exception e) {

			LOG.error(e.toString());
			throw new FileUploadException("upload file error", e);
		}

		// 创建配置文件表格
		ConfNewItemForm confNewItemForm = new ConfNewItemForm(confNewForm);
		confNewItemForm.setKey(file.getOriginalFilename());
		confNewItemForm.setValue(fileContent);

		// 业务校验
		configValidator.validateNew(confNewItemForm, DisConfigTypeEnum.FILE);

		//
		configMgr.newConfig(confNewItemForm, DisConfigTypeEnum.FILE);

		// HTTP联动操作
		if (getSysc()) {
			final ConfNewForm confNewFormT=confNewForm;
			final MultipartFile fileT=file;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.updateFileSync(confNewFormT, fileT);
					LOG.info("sync add file " + i);
				}
			});
		}

		return buildSuccess("创建成功");
	}

	/**
	 * 配置文件的新建(使用文本)
	 *
	 * @param confNewForm
	 * @param fileContent
	 * @param fileName
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/filetext", method = RequestMethod.POST)
	public JsonObjectBase updateFileWithText(@Valid ConfNewForm confNewForm, @NotNull String fileContent,
			@NotNull String fileName) {

		LOG.info(confNewForm.toString());

		// 创建配置文件表格
		ConfNewItemForm confNewItemForm = new ConfNewItemForm(confNewForm);
		confNewItemForm.setKey(fileName);
		confNewItemForm.setValue(fileContent);

		// 业务校验
		configValidator.validateNew(confNewItemForm, DisConfigTypeEnum.FILE);

		//
		configMgr.newConfig(confNewItemForm, DisConfigTypeEnum.FILE);
		
		// HTTP联动操作
		if (getSysc()) {
			final ConfNewForm confNewFormT=confNewForm;
			final String fileContentT=fileContent;
			final String fileNameT=fileName;
			
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.updateFileWithTextSync(confNewFormT, fileContentT, fileNameT);
					LOG.info("sync add file with text " + i);
				}
			});
		}

		return buildSuccess("创建成功");
	}
}
