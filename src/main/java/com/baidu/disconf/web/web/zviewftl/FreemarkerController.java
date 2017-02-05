package com.baidu.disconf.web.web.zviewftl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.dsp.common.annotation.NoAuth;

@Controller
public class FreemarkerController {
	
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
	@RequestMapping({"modifypassword"})
	public String modifypassword(){
		return "/modifypassword";
	}
	
	@NoAuth
	@RequestMapping({"configUsage"})
	public String configUsage(){
		return "/configUsage";
	}
	
}
