package com.zxx.blog.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zxx.blog.model.request.Response;
import com.zxx.blog.util.io.UploadUtils;

@RequestMapping("/blog/auth")
@RestController
public class UploadController {
	
	@Autowired
	private UploadUtils uploadUtils;
	
	@RequestMapping("/upload")
	public Response upload(@RequestParam("file") MultipartFile file) {
		return Response.success(uploadUtils.saveFile(file));
	}
}
