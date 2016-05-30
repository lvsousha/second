package com.stone.action.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.mapper.BasicMapper;
import com.stone.model.Basic;
import com.stone.model.table.TableFilter;

@Controller
@RequestMapping("")
public class ExampleController {
	
	@Autowired
	BasicMapper basicMapper;
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private Map<Object, Object> returnMap = new HashMap<>();
	
	@RequestMapping(value="example")
	public ModelAndView example(){
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", 0);
		parameters.put("limit", 1000);
		List<Basic> basics = basicMapper.selectAll(parameters);
		ModelAndView mav = new ModelAndView("example/basic");
		mav.addObject("basics",basics);
//		System.out.println(basics.size());
		return mav;
	}
	
	@RequestMapping(value="example/list")
	public void examplelist(HttpServletRequest request, HttpServletResponse response, Integer length, TableFilter data){
		Map<String,Integer> parameters = new HashMap<>();
		System.out.println(data.getLength());
		parameters.put("start", data.getStart());
		parameters.put("limit", data.getLength());
		List<Basic> basics = basicMapper.selectAll(parameters);
		Integer total = basicMapper.count();
		returnMap.put("data", basics);
		returnMap.put("recordsFiltered", total);
		returnMap.put("draw", data.getDraw());
		returnMap.put("recordsTotal", total);
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath*:spring-context.xml");
		BasicMapper basicMapper = (BasicMapper) ac.getBean("basicMapper");
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", 0);
		parameters.put("limit", 1000);
		List<Basic> basics = basicMapper.selectAll(parameters);
		System.out.println(basics.size());
	}
	
}
