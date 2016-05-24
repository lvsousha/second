package com.stone.spend;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.action.StoneController;
import com.stone.mapper.SpendMapper;
import com.stone.mapper.UserMapper;
import com.stone.model.Spend;
import com.stone.model.User;


@Controller
@RequestMapping("")
public class SpendController {
	
	@Autowired
	StoneController stoneController;
	@Autowired
	SpendMapper spendMapper;
	@Autowired
	UserMapper userMapper;
	
	private Map<Object, Object> returnMap = new HashMap<>();
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
	
	@RequestMapping(value="spendlist")
	public ModelAndView spendList(){
		System.out.println("访问spendList");
		ModelAndView mav = new ModelAndView("spend/editable");
		mav.addObject("left",stoneController.getLeftBar());
		mav.addObject("listcontent",spendMapper.selectSpends());
		return mav;
//		return "index/index";
	}
	
	@RequestMapping(value="spend/deleteRow")
	public void deleteRow(HttpServletRequest request, HttpServletResponse response, Spend spend){
		System.out.println("访问deleteRow");
		spendMapper.delete(spend.getId());
		returnMap.put("success", true);
		try {
			response.getWriter().println(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="spend/insertRow")
	public void insertRow(HttpServletRequest request, HttpServletResponse response, Spend spend){
		System.out.println("访问insertRow");
		User user = userMapper.selectUserByUsername("stone");
		spend.setCreatedby(user);
		spend.setUpdatedby(user);
		spend.setCreated(new Date());
		spend.setUpdated(new Date());
		spendMapper.insert(spend);
		returnMap.put("success", true);
		returnMap.put("spend", spend);
		try {
			response.getWriter().println(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="spend/updateRow")
	public void updateRow(HttpServletRequest request, HttpServletResponse response, Spend spend){
		System.out.println("访问insertRow");
		Spend after = spendMapper.selectSpend(spend.getId());
		after.setSubject(spend.getSubject());
		after.setPrice(spend.getPrice());
		after.setUpdated(new Date());
		spendMapper.update(after);
		returnMap.put("success", true);
		returnMap.put("spend", after);
		try {
			response.getWriter().println(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
