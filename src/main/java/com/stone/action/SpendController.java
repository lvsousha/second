package com.stone.action;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.mapper.SpendMapper;
import com.stone.mapper.UserMapper;
import com.stone.model.Spend;
import com.stone.model.User;
import com.stone.model.table.TableFilter;
import com.stone.service.DataTableService;

import net.sf.jxls.transformer.XLSTransformer;


@Controller
@RequestMapping("")
public class SpendController {
	
	@Autowired
	StoneController stoneController;
	@Autowired
	SpendMapper spendMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	DataTableService dataTableService;
	
	private Map<Object, Object> returnMap = new HashMap<>();
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
	
	
	@RequestMapping(value="spend/list")
	public void list(HttpServletRequest request, HttpServletResponse response, String data){
		TableFilter datas = gson.fromJson(data,new TableFilter().getClass());
		Map<String,Object> parameters = dataTableService.getFilter(datas,"spe_");
		System.out.println(parameters.get("start"));
		System.out.println(parameters.get("limit"));
		List<Spend> Spends = spendMapper.select(parameters);
		Integer total = spendMapper.count(parameters);
		returnMap.put("data", Spends);
		returnMap.put("recordsFiltered", total);
		returnMap.put("draw", datas.getDraw());
		returnMap.put("recordsTotal", total);
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="spendlist")
	public ModelAndView spendList(){
		System.out.println("访问spendList");
		ModelAndView mav = new ModelAndView("spend/spend");
		mav.addObject("left",stoneController.getLeftBar());
		mav.addObject("listcontent",spendMapper.selectSpends());
		mav.addObject("parentName","Spend");
		mav.addObject("childName","SpendList");
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
		returnMap.clear();
		if(spend.getSubject() != null && !spend.getSubject().equals("") && spend.getPrice() != null && !spend.getPrice().equals("")){
			User user = userMapper.selectUserByUsername("stone");
			spend.setCreatedby(user);
			spend.setUpdatedby(user);
			spend.setCreated(new Date());
			spend.setUpdated(new Date());
			spendMapper.insert(spend);
			returnMap.put("success", true);			
		}else{
			returnMap.put("success", false);	
		}
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
	
	@RequestMapping(value="spend/export")
	public void export(HttpServletRequest request, HttpServletResponse response, Spend spend){
		List<Spend> spends = spendMapper.selectSpends();
		Map<String,List<Spend>> map = new HashMap<String, List<Spend>>();
//		String templateDir =request.getSession().getServletContext().getRealPath("/")+"export/template.xls"; 
//		String templateDir =this.getClass().getClassLoader().getResource("").getPath()+"export/template.xls";
//		String templateDir =request.getSession().getServletContext().getRealPath("/")+"export/template.xls";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("/export/template.xls");
		map.put("spends", spends);
		XLSTransformer transformer = new XLSTransformer(); 
		try {
//			Workbook wb = transformer.transformXLS(new FileInputStream(templateDir), map);
			Workbook wbs = transformer.transformXLS(is, map);
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
			response.addHeader("Content-Disposition", "attachment;filename=spendList.xls");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			wbs.write(toClient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
