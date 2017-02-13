package com.baidu.wonder.other;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.baidu.disconf.web.service.data.bo.DataSql;

public class Test {
	public static void main(String[] args) throws Exception {
		write();
		read();
	}

	public static void write() throws Exception {
		List<DataSql> list = new ArrayList<>();
		DataSql e = new DataSql();
		e.setInsertSql("insert into tab(id,name) values(1,'app')");
		e.setUpdateSql("update tab set name='haha' where id=1");
		list.add(e);
		//
		FileOutputStream out = new FileOutputStream("test");
		ObjectOutputStream objout = new ObjectOutputStream(out);
		objout.writeObject(list);
		objout.flush();
		objout.close();
		out.close();
		//
	}
	
	public static void read() throws Exception {
		FileInputStream ins=new FileInputStream("test");
		ObjectInputStream objins=new ObjectInputStream(ins);
		Object obj = objins.readObject();
		System.out.println("--->obj:\n\t"+obj);
		objins.close();
		ins.close();
		//
	}

	
	
}
