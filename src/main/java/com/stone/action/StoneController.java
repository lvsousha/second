package com.stone.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StoneController {

	@RequestMapping(value="login")
	public String example(){
		return "login/login";
	}
	
	@RequestMapping(value="index")
	public String index(){
		return "index/index";
	}

}
