package com.baidu.disconf.web.web.data.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.service.DataMgr;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/data")
public class DataController  extends BaseController {
	protected static final Logger LOG = LoggerFactory.getLogger(DataController.class);
	
	@Autowired
    private DataMgr dataMgr;
	
	@RequestMapping(value = "/getCount", method = RequestMethod.GET)
    @ResponseBody
    public Data getCount() {
		Data d = dataMgr.getData();
    	return d;
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase list() {
		List<Data> ds = dataMgr.getDataList();
    	return buildListSuccess(ds, ds.size());
    }
	
}
