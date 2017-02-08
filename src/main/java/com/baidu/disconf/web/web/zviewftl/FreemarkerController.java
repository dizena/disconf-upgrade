package com.baidu.disconf.web.web.zviewftl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.dsp.common.annotation.NoAuth;

@Controller
public class FreemarkerController {
	private static Logger log=LoggerFactory.getLogger(FreemarkerController.class);
	
	@NoAuth
	@RequestMapping({"/","","index"})
	public String index(){
		return "/index";
	}
	
	@NoAuth
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@NoAuth
	@RequestMapping(value="main",method=RequestMethod.GET)
	public ModelAndView main(){
		return new ModelAndView("main");
	}
	
	
	@NoAuth
	@RequestMapping({"newapp"})
	public String newapp(){
		return "/newapp";
	}
	
	@NoAuth
	@RequestMapping({"app"})
	public String app(){
		return "/app";
	}
	
	@NoAuth
	@RequestMapping({"newconfig_item"})
	public String newconfig_item(){
		return "/newconfig_item";
	}
	
	@NoAuth
	@RequestMapping({"newconfig_file"})
	public String newconfig_file(){
		return "/newconfig_file";
	}
	
	@NoAuth
	@RequestMapping({"modifyFile"})
	public ModelAndView modifyFile(@RequestParam("configId") String configId){
		log.info("modifyFile:"+configId);
		ModelAndView mv=new ModelAndView("modifyFile");
		mv.addObject("configId", configId);
		return mv;
	}
	
	@NoAuth
	@RequestMapping({"modifyItem"})
	public ModelAndView modifyItem(@RequestParam("configId") String configId){
		log.info("modifyItem:"+configId);
		ModelAndView mv=new ModelAndView("modifyItem");
		mv.addObject("configId", configId);
		return mv;
	}
	
	@NoAuth
	@RequestMapping({"modifypassword"})
	public String modifypassword(){
		return "/modifypassword";
	}
	
	@NoAuth
	@RequestMapping({"configUsage"})
	public String configUsage(){
		return "/configUsage";
	}
	
	
	@NoAuth
	@RequestMapping({"area"})
	public String area(){
		return "/area";
	}
	
	@NoAuth
	@RequestMapping({"areaadd"})
	public String areaadd(){
		return "/areaadd";
	}
	
	
	@NoAuth
	@RequestMapping({"data"})
	public String datasync(){
		return "/data";
	}
	
	
	
	
	
	
	
	
	
	
	
}
