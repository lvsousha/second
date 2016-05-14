package com.stone.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stone.common.Encryption;
import com.stone.mapper.UserMapper;
import com.stone.model.User;


public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("start:"+(new Date()));
		ApplicationContext ac = new ClassPathXmlApplicationContext(
						"classpath*:spring-context.xml");
		UserMapper userMapper = (UserMapper) ac.getBean("userMapper");
		User user = new User();
		user.setUsername("stone");
		user.setPassword("stone");
		user.setPassword(Encryption.getMD5Str(user.getPassword()));
		user.setEmail("771733227@qq.com");
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userMapper.insertUser(user);
		System.out.println("end:"+(new Date()));
	}

}
