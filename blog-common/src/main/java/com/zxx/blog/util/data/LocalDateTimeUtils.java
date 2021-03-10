package com.zxx.blog.util.data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.zxx.blog.constant.SystemFinal;

public class LocalDateTimeUtils {
	
	public static LocalDateTime now() {
		return LocalDateTime.now();
	}
	
	public static Long getNowTimestamp() {
		return System.currentTimeMillis();
	}
	
	public static Long getTimestamp(LocalDateTime local) {
		return local.toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}
	
	public static String dateToStr(LocalDateTime local,String format) {
		format = format == null ? SystemFinal.DATE_FORMAT : format;
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern(format);
		return dtf.format(local);
	}
	
	public static void main(String[] args) {
		LocalDateTime local = LocalDateTime.of(2000, 2, 29, 1, 1);  //当前时间
		local = local.plusYears(1L);  //当前时间+1年后的当前时间
		System.out.println(local);
	}
}
