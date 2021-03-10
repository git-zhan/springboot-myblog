package com.zxx.blog.util.io;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.zxx.blog.constant.SystemFinal;
import com.zxx.blog.model.exception.ClientException;
import com.zxx.blog.util.data.LocalDateTimeUtils;
import com.zxx.blog.util.data.StringUtils;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("upload")
public class UploadUtils {
	
	private String savePath;
	
	public String saveFile(MultipartFile mfile) {
		if(mfile == null) {
			throw new ClientException("未检测到上传文件!");
		}
		String destpath = generateFilePath(mfile.getOriginalFilename(), null);
		File file = new File(savePath + destpath);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				mfile.transferTo(file);
			} catch (IOException e) {
				throw new ClientException(e);
			}
		}
		return destpath;
	}
	
	//生成一个文件路径
	public static String generateFilePath(String oldFileName,String newFileName) {
		String date = LocalDateTimeUtils.dateToStr(LocalDateTimeUtils.now(), SystemFinal.DATE_FORMAT_DATE);
		String subfix = oldFileName.substring(oldFileName.lastIndexOf("."));
		String fileName = StringUtils.isBlank(newFileName) ? LocalDateTimeUtils.getNowTimestamp() + subfix : newFileName + subfix ;
		return date + File.separator + fileName;
	}
}
