package com.stone.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {

	@SuppressWarnings("resource")
	public StringBuffer readTxtFile(File file){
		StringBuffer sb = new StringBuffer();
		try{
			BufferedReader bd = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String s = bd.readLine();
			while(s != null){
				sb.append(s);
				s = bd.readLine();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return sb;
	}

	/**
	 * 首字母大写
	 * @param str 需要该行为的字符串
	 * @return
	 */
	public static String firstToUpcase(String str){
		byte[] bytes = str.getBytes();
		bytes[0] = (byte) (bytes[0] - 32);
		return new String(bytes);
	}

	/**
	 * 获取属性文件里某个属性的值
	 * @param path 路径
	 * @param property 属性名（键）
	 * @return
	 */
	public static String getProperty(String path, String property){
		ResourceBundle rb = java.util.ResourceBundle.getBundle(path); //path=properties.sqlserver.jdbc
		return rb.getString(property);
	}

	/**
	 * 获取上传文件的输入流
	 * @param request  web请求对象
	 * @return
	 * @throws IOException
	 */
	public static MultipartFile getValueFromFilefield(HttpServletRequest request) throws IOException{
	      MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	      //获得上传文件的名称
	      Iterator<String> iter = multiRequest.getFileNames();
	      //如果有的话就依次取出来
	      while (iter.hasNext()){
	        //包装过的文件流
	        MultipartFile file = multiRequest.getFile((String)iter.next());
	        //这里要进行判断，即使是空值，没有上传内容，file都是有值（空值）的，而文件流大小要大于0才是有上传的东西
	        if (file.getSize() > 0)   
				return file;            
	        }		
	      return null;
	}

	/**
	 * 非递归方法遍历某个路径的文件
	 * @param path 路径
	 */
	public static void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    fileNum++;
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath());
                    folderNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        fileNum++;
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        folderNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
    }
	
	/**
	 * 采用递归的方法遍历文件
	 * @param path 路径
	 */
	public static void traverseFolder2(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	/**
	 * 获取该路径下所有为该后缀的文件
	 * @param strPath 路径
	 * @param suffix 后缀(.avi)
	 */
	public static void getFileList(String strPath, String suffix) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath(), suffix); // 获取文件绝对路径
                } else if (fileName.endsWith(suffix)) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                } else {
                    continue;
                }
            }

        }
    }
	
	/**
	 * 向文件添加信息
	 * @param outFileName
	 * @param data
	 */
	public void writeData(String outFileName,String data){
		FileWriter fw = null;
		try{
			File file = new File(outFileName);
			fw = new FileWriter(file,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(data);
			pw.flush();
			fw.flush();
			pw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
