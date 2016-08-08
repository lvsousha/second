package com.stone.action.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class StoneInterceptor extends HandlerInterceptorAdapter {


	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handle) throws Exception {
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		if(!url.contains("login")){
			if(username == null || username.toString().equals("")){
				response.sendRedirect("/second/login");
				return false;
			}			
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object object, ModelAndView mav) throws Exception {
		
		
	}
	
}
