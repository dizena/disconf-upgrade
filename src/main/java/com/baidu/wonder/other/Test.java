package com.baidu.wonder.other;

import java.util.ArrayList;
import java.util.List;

import com.baidu.wonder.other.bean.OtherServer;

public class Test {
	public static void main(String[] args) {
		System.out.println(getRemote());
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
