package com.stone.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Example {

	public static void main(String[] args) {
		try{			
			Class<?> c = Class.forName("java.lang.Integer");  c.newInstance();
			//获取所有的属性?  
			Field[] fs = c.getDeclaredFields();  
			
			//定义可变长的字符串，用来存储属性  
			StringBuffer sb = new StringBuffer();  
			//通过追加的方法，将每个属性拼接到此字符串中  
			//最外边的public定义  
			sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");  
			//里边的每一个属性  
			for(Field field:fs){  
				sb.append("\t");//空格  
				sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等  
				sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字  
				sb.append(field.getName()+";\n");//属性的名字+回车  
			}  
			
			sb.append("}");  
			
			System.out.println(sb);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
