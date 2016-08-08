package com.stone.common;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static String fileName = "/config.properties";
	
	public static String  getProperties(String key){  
        Properties p = new Properties();  
        String value = "";
        try {  
            InputStream in = Config.class.getResourceAsStream(fileName);//这里有人用new FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。  
            p.load(in); 
            value = p.getProperty(key);
            in.close();
        } catch (Exception e) {  
              
        }  
        return value;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Config.getProperties("upload"));
	}

}
