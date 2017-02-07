package com.baidu.wonder.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtils {
	private static final String FILENAME="servers.properties";
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
}
