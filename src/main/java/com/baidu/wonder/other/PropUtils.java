package com.baidu.wonder.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtils {
	private static final String FILENAME="area.properties";
	private static Properties prop=new Properties();
	static{
		ClassLoader loader=PropUtils.class.getClassLoader();
		try{
			InputStream in=loader.getResourceAsStream(FILENAME);
			prop.load(in);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static String getKey(String key){
		return prop.getProperty(key);
	}
	
	public static Long getLocalAreaId(){
		String s= prop.getProperty("localArea");
		Long l=null;
		if(s!=null){
			l=Long.parseLong(s);
		}
		return l;
	}
	
}
