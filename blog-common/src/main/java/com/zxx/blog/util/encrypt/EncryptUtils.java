package com.zxx.blog.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	public static byte[] encrypt(String content,String encryType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytes = null;
		if(encryType.equals("MD5")) {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(content.getBytes("UTF-8"));
			bytes = md5.digest();
		}
		return bytes;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		encrypt("ehllo", "MD5");
	}
}
