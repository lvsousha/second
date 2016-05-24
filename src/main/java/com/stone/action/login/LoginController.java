package com.stone.action.login;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.common.Check;
import com.stone.common.Encryption;
import com.stone.mapper.UserMapper;
import com.stone.model.EmailModel;
import com.stone.model.User;

@Controller
@RequestMapping("content/")
public class LoginController {
	
	protected final Log logger = LogFactory.getLog(getClass());

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
			user.setCreated(new Date());
			user.setUpdated(new Date());
			userMapper.insertUser(user);
			request.getSession().setAttribute("username", username);
			returnMap.put("success", true);
		}
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="forgetPassword")
	public void forgetPassword(HttpServletRequest request, HttpServletResponse response){
		String email = request.getParameter("email");
		User user = null;
		Integer id = null;
		if(Check.isEmail(email)){
			user = userMapper.selectUserByEmail(email);
			if(user != null){
				id = user.getId();
				String password = user.getUsername() + String.valueOf(id);
				String encodePassword = Encryption.getMD5Str(password);
				Properties prop = new Properties();   
		        InputStream in = getClass().getResourceAsStream("/email.properties");
		        try {
					prop.load(in);
				} catch (IOException e) {
					e.printStackTrace();
				} 
		        EmailModel mail = new EmailModel();
		        mail.setHost(prop.getProperty("emailHost")); // 设置邮件服务器,如果不用163的,自己找找看相关的
		        mail.setSender(prop.getProperty("emailSender"));
		        mail.setReceiver(email); // 接收人
		        mail.setUsername(prop.getProperty("emailUsername")); // 登录账号,一般都是和邮箱名一样吧
		        mail.setPassword(prop.getProperty("emailPassword")); // 发件人邮箱的登录密码
				if(sendPasswordEmail(mail, password)){
					returnMap.put("success", true);
					user.setUpdated(new Date());
					user.setPassword(encodePassword);
					userMapper.update(user);
				}else{
					returnMap.put("success", false);
					returnMap.put("errorMessage", " 发送邮件到 " + mail.getReceiver()+ " 失败");
				}
			}else{
				returnMap.put("success", false);
				returnMap.put("errorMessage", "email is not Existed ");
			}
		}
		try {
			response.getWriter().print(gson.toJson(returnMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean sendPasswordEmail(EmailModel mail, String password){
		try {
	    	 Properties prop = new Properties();
	         prop.setProperty("mail.host", mail.getHost());
	         prop.setProperty("mail.transport.protocol", "smtp");
	         prop.setProperty("mail.smtp.auth", "true");
	         Session session = Session.getInstance(prop);//1、创建session
//	         session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	         Transport ts = session.getTransport();//2、通过session得到transport对象
	         ts.connect(mail.getHost(), mail.getSender(), mail.getPassword());
	         Message message = createMail(session, mail, password);//4、创建邮件
	         ts.sendMessage(message, message.getAllRecipients());//5、发送邮件
	         ts.close();
   	 	} catch (Exception e) {
          e.printStackTrace();
          logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()+ " 失败");
          return false;
   	 	}
		return true;
	}
	
	public static MimeMessage createMail(Session session, EmailModel mail, String password) throws Exception {
   	 MimeMessage message = new MimeMessage(session);//创建邮件
        message.setFrom(new InternetAddress(mail.getSender()));//发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));//收件人

        message.setSubject("New password");//邮件标题
        MimeBodyPart text = new MimeBodyPart();// 准备邮件正文数据
        text.setContent(password, "text/html;charset=UTF-8");
        MimeMultipart mm = new MimeMultipart("mixed");// 描述数据关系
        mm.addBodyPart(text);
        message.setContent(mm);
        message.saveChanges();

        message.writeTo(new FileOutputStream("/ImageMail.eml"));//将创建好的邮件写入到E盘以文件的形式进行保存

        return message;//返回创建好的邮件
    }
	
	
	public static void main(String[] args) {
		InputStream in = Object.class.getResourceAsStream("/second/email.properties");
		System.out.println(in);

	}

}
