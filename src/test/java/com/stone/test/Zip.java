package com.stone.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Zip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String zippath = "e:\\Java 并发编程实战.zip";
            File file = new File(zippath);
            System.out.println(file.exists());
            File outFile = null;
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while((entry = zipInput.getNextEntry()) != null){
                System.out.println("解压缩" + entry.getName() + "文件");
                outFile = new File("/Test" + File.separator + entry.getName());
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdir();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while((temp = input.read()) != -1){
                    output.write(temp);
                }
                input.close();
                output.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
