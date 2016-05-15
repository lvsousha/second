package com.stone.action.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.common.Check;
import com.stone.common.Encryption;
import com.stone.mapper.UserMapper;
import com.stone.model.User;

@Controller
@RequestMapping("content/")
public class LoginController {

	@Autowired
	private UserMapper userMapper;
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private Map<Object, Object> returnMap = new HashMap<>();
	
	@RequestMapping(value="login")
	public void login(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = Encryption.getMD5Str(password);
		User user = userMapper.selectUserByUsername(username);
		if(user == null){
			returnMap.put("success", false);
			returnMap.put("errorMessage", "用户名错误");
		}else if(user.getPassword().equals(password)){
			returnMap.put("success", true);
			request.getSession().setAttribute("username", username);
		}else{
			returnMap.put("success", false);
			returnMap.put("errorMessage", "密码错误");
		}
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="register")
	public void register(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		User user = null;
		Map<String, String> parameters = new HashMap<>();
		parameters.put("username", username);
		parameters.put("email", email);
		password = Encryption.getMD5Str(password);
		user = userMapper.selectUserByUsernameOrEmail(parameters);
		if(!Check.isEmail(email)){
			returnMap.put("success", false);
			returnMap.put("errorMessage", "email is wrong");
		}else if(user != null){
			returnMap.put("success", false);
			returnMap.put("errorMessage", "usename or email is Existed ");
		}else{			
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			userMapper.insertUser(user);
			request.getSession().setAttribute("username", username);
		}
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="forgetPassword")
	public String forgetPassword(HttpServletRequest request, HttpServletResponse response){
		System.out.println("in");
		return "login/index";
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
