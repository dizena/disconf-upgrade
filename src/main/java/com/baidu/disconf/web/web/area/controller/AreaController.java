package com.baidu.disconf.web.web.area.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.service.AreaMgr;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;

@Controller
@RequestMapping(WebConstants.API_PREFIX + "/area")
public class AreaController extends BaseController {
	protected static final Logger LOG = LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
    private AreaMgr areaMgr;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase list() {

		List<Area> envListVos =areaMgr.list();
        return buildListSuccess(envListVos, envListVos.size());
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjectBase add(Area area) {
		areaMgr.addArea(area);
    	return buildSuccess("创建成功");
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase delete(@RequestParam("id")Long id) {
		areaMgr.delArea(id);
    	return buildSuccess("删除成功");
    }
	
}
