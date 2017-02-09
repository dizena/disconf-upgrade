package com.baidu.wonder.other;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.web.service.area.bo.Area;

public class ServerUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ServerUtil.class);
	
	private static String areaid = PropUtils.getKey("localArea");

	private static Long area_id = Long.parseLong(areaid);
	
	private static List<Area> areas;
	
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 50, 10,TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100)); 
	
	public static void execute(Runnable command){
		threadPool.execute(command);
	}
	
	public static void get(){
		
	}
	
	
	
}
