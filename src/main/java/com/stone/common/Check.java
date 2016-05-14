package com.stone.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

	public static Boolean isEmail(String email){
//		[\\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\.)+[\\w](?:[\\w-]*[\\w])?
		String pattern = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])";
		Pattern p = Pattern.compile(pattern);  
	      Matcher m = p.matcher(email);
	        return m.matches(); 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Check.isEmail("771133221@qq.com"));
	}

}
