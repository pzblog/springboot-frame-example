package com.example.boot.core.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.boot.core.error.CommonErrorMsg;
import com.example.boot.core.result.BuildResponseMsg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebUtil {
	
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();
	
	public static void bindRequestAndResponse(HttpServletRequest request) {
		threadLocal.set(request);
    }
	
	public static void buildPrintWriter(HttpServletResponse response,Object object) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSON(object));
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("统一封装返回信息失败！",e);
		}
	}
	
	public static String getWebAppPath(String filePath) {
		HttpServletRequest request = threadLocal.get();
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		Path rootPath = Paths.get(contextPath).getParent().getParent();
		String fileDirectyPath = rootPath.toString() + File.separatorChar + filePath;
		File fileDirecty = new File(fileDirectyPath);
		if(!fileDirecty.isDirectory()) {
			fileDirecty.mkdirs();
		}
		return fileDirecty.getAbsolutePath();
	}
}
