package com.stone.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.action.StoneController;
import com.stone.common.Config;
import com.stone.mapper.SpendMapper;
import com.stone.mapper.UserMapper;
import com.stone.model.Spend;
import com.stone.model.User;
import com.stone.model.table.TableFilter;
import com.stone.service.DataTableService;


@Controller
@RequestMapping("")
public class UploadController {
	
	@Autowired
	StoneController stoneController;
	@Autowired
	SpendMapper spendMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	DataTableService dataTableService;
	
	private Map<Object, Object> returnMap = new HashMap<>();
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
	
	@RequestMapping(value="uploadFile")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("upload/upload");
		mav.addObject("left",stoneController.getLeftBar());
		mav.addObject("parentName","Upload");
		mav.addObject("childName","UploadFile");
		return mav;
	}
	
	@RequestMapping(value="upload/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response){  
		File fold = new File(Config.getProperties("upload")+"/"+request.getSession().getAttribute("username"));
		if(!fold.exists()){
			fold.mkdirs();
		}
		System.out.println(fold.getPath());
    	//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(!myFileName.trim().equals("")){  
                        System.out.println(myFileName);
                        //重命名上传后的文件名  
                        String fileName = "Upload--" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = fold.getPath()+"/"+ fileName;  
                        File localFile = new File(path);  
                        try {
							file.transferTo(localFile);
						} catch (Exception e) {
							e.printStackTrace();
						}  
                    }
                }  
            }  
              
        }  
    }	
}
