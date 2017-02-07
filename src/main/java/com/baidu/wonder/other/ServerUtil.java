package com.baidu.wonder.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.baidu.wonder.other.bean.OtherServer;

public class ServerUtil {

	// 根据配置文件得到本机服务和远端服务；得到远端服务的用户/密码;
	public static List<OtherServer> servers = getRemote();

	// RemoteDisconfUtil.execute(url,params,post/get);execute里在进行多服务的login更新执行；

	public static void addApp(AppNewForm appNewForm) {
		try {
			for (OtherServer server : servers) {
				DisconfRemoteBizAppApi api = new DisconfRemoteBizAppApi(server.getHost());
				api.addapp(appNewForm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<OtherServer> getRemote() {
		List<OtherServer> list = new ArrayList<>();
		String auths = PropUtils.getKey("auths");
		String remotes = PropUtils.getKey("remotes");
		String[] sss1 = auths.split(",");
		String[] sss2 = remotes.split(",");
		if (sss1 != null && sss1.length > 0 && sss1.length == sss2.length) {
			for (int i = 0; i < sss1.length; i++) {
				String ss1 = sss1[i];
				String[] s1 = ss1.split("\\|");
				OtherServer os = new OtherServer();
				os.setHost(sss2[i]);
				os.setName(s1[0]);
				os.setPasswd(s1[1]);
				list.add(os);
			}
		}
		return list;
	}

}
