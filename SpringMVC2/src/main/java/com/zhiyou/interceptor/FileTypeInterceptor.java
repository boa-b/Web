package com.zhiyou.interceptor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FileTypeInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;

		// 如果输文件上传的请求 name一定会实现MultipartHttpServletRequest 接口
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest mu = (MultipartHttpServletRequest) request;
			// 获得请求中的所有文件map
			Map<String, MultipartFile> fileMap = mu.getFileMap();
			// 获得map中的所有key
			Iterator<String> iterator = fileMap.keySet().iterator();
			// 迭代key
			while (iterator.hasNext()) {
				// 挨个获得key
				String forkey = iterator.next();
				// 根据key取出文件
				MultipartFile file = mu.getFile(forkey);
				if (file.getSize() > (1024 * 1024)) {
					int number = (int) file.getSize() / 1024;
					request.setAttribute("msg", "上传文件位" + number + "M,请上传图片");
					request.getRequestDispatcher("Edit.jsp").forward(request, response);
					flag = false;
					break;
				}

				// 取出文件名
				String filename = file.getOriginalFilename();
				// 判断文件名是否合法
				if (!chenFile(filename)) {
					request.setAttribute("msg", "上传文件类型错误,请上传图片");
					request.getRequestDispatcher("Edit.jsp").forward(request, response);
					flag = false;
				}
			}

		}

		return flag;
	}

	public boolean chenFile(String fileName) {

		// 代表放行的类型
		String suff[] = { "jpg", "gif", "png", "jpeg" };
		// 截取文件后缀
		String suffx = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 判断suff中是否包含文件后缀 去掉空格转成小写
		if (Arrays.asList(suff).contains(suffx.trim().toLowerCase())) {
			return true;// 包含代表放行
		}
		return false;// 不包含代表返回false
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
