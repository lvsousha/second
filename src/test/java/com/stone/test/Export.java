package com.stone.test;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stone.mapper.SpendMapper;
import com.stone.model.Spend;

import net.sf.jxls.transformer.XLSTransformer;

public class Export {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath*:spring-context.xml");
		String templateDir = "/export/template.xls";  
		String targetDir="D:/test2.xls";  
		SpendMapper sm = (SpendMapper)ac.getBean("spendMapper");
		List<Spend> spends = sm.selectSpends();
		Map<String,List<Spend>> map = new HashMap<String, List<Spend>>();
		map.put("spends", spends);
		XLSTransformer transformer = new XLSTransformer(); 
		try {
			OutputStream toClient = new BufferedOutputStream(new FileOutputStream("D:/test5.xls")); 
//			transformer.transformXLS(templateDir, map, targetDir);
			org.apache.poi.ss.usermodel.Workbook wb = transformer.transformXLS(new FileInputStream(templateDir), map);
			wb.write(toClient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
