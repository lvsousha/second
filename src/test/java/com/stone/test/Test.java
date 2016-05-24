package com.stone.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stone.common.Encryption;
import com.stone.mapper.SpendMapper;
import com.stone.mapper.UserMapper;
import com.stone.model.Spend;
import com.stone.model.User;


public class Test {

	public static void main(String[] args) {
		System.out.println("start:"+(new Date()));
		Test test = new Test();
		System.out.println("start:"+(new Date()));
		ApplicationContext ac = new ClassPathXmlApplicationContext(
						"classpath*:spring-context.xml");
		test.createSpend(ac);
		System.out.println("end:"+(new Date()));
	}
	
	public void createSpend(ApplicationContext ac){
		System.out.println("in");
		SpendMapper spendMapper = (SpendMapper) ac.getBean("spendMapper");
		UserMapper userMapper = (UserMapper) ac.getBean("userMapper");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, -1);
		Spend spend = new Spend();
		spend.setCreatedby(userMapper.selectUserByUsername("stone"));
		spend.setUpdatedby(userMapper.selectUserByUsername("stone"));
		spend.setSubject("晚餐");
		spend.setPrice(new BigDecimal(18));
		spend.setCreated(cal.getTime());
		spend.setUpdated(cal.getTime());
		spendMapper.insert(spend);
		System.out.println(spend.getId());
	}
	
	public void createUser(ApplicationContext ac){
		UserMapper userMapper = (UserMapper) ac.getBean("userMapper");
		User user = new User();
		user.setUsername("stone");
		user.setPassword("stone");
		user.setPassword(Encryption.getMD5Str(user.getPassword()));
		user.setEmail("771733227@qq.com");
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userMapper.insertUser(user);
	}

}
