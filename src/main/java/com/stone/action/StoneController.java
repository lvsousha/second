package com.stone.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.mapper.MenuMapper;
import com.stone.model.Menu;

@Controller
@RequestMapping("")
public class StoneController {

	@Autowired
	MenuMapper menuMapper;
	
	@RequestMapping(value="login")
	public String example(){
		System.out.println("访问login");
		return "login/login";
	}
	
	@RequestMapping(value="index")
	public ModelAndView index(){
		System.out.println("访问index");
		ModelAndView mav = new ModelAndView("index/index");
		mav.addObject("left",getLeftBar());
		return mav;
//		return "index/index";
	}
	
//	@RequestMapping(value="spendlist")
//	public ModelAndView spendList(){
//		System.out.println("访问spendList");
//		ModelAndView mav = new ModelAndView("spend/list");
//		mav.addObject("left",getLeftBar());
//		return mav;
////		return "index/index";
//	}
	
	public Map<String, List<Menu>> getLeftBar(){
		List<Menu> menus = menuMapper.selectParentIsNull();
		Map<String, List<Menu>> map = new HashMap<>();
		for(Menu menu : menus){
			List<Menu> children = menuMapper.selectMenuByParent(menu.getId());
			map.put(menu.getMenuname(), children);
		}
		return map;
	}

}
