package com.baidu.disconf.web.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPools {

	public static ExecutorService pool = Executors.newFixedThreadPool(10);

	public static void execute(Runnable command) {
		pool.execute(command);
	}

}
