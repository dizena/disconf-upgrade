package test;

import java.io.IOException;
import java.util.List;

import com.baidu.disconf.web.service.data.bo.Data;
import com.baidu.disconf.web.service.data.bo.DataSql;
import com.baidu.wonder.other.DisconfRemoteBizDataApi;

public class DataSync {

	public static void main(String[] args) throws IOException {

		DisconfRemoteBizDataApi t = new DisconfRemoteBizDataApi("http://127.0.0.1:56789");

		if (t.session() || t.login("admin", "admin")) {

			Data d = t.getData();

			List<DataSql> datas = t.db2Api();

			// t.api2Db(datas);

			t.close();

			System.out.println("data:" + d + ",datas:" + datas.size());
		} else {
			System.out.println("系统有问题");
		}
		
		//end
	}

}
