package com.baidu.disconf.web.service.sync.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.dao.AreaDao;
import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;
import com.baidu.disconf.web.service.sync.service.SyncMgr;
import com.baidu.wonder.other.DisconfRemoteBizAppApi;
import com.baidu.wonder.other.DisconfRemoteBizAreaApi;
import com.baidu.wonder.other.DisconfRemoteBizItemApi;
import com.baidu.wonder.other.PropUtils;

@Service
public class SyncMgrImpl implements SyncMgr {
	public static Logger log = LoggerFactory.getLogger(SyncMgrImpl.class);

	@Autowired
	private AreaDao areaDao;

	private Long area_id;
	private List<Area> areas;
	private int otherCount;

	private void init() {
		if (area_id == null) {
			area_id = PropUtils.getLocalAreaId();
		}
		if (areas == null) {
			areas = areaDao.findAll();

			if (areas != null && areas.size() > 0) {
				otherCount = areas.size() - 1;
			}
		}

	}

	@Override
	public int addAppSync(AppNewForm appNewForm) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizAppApi api = new DisconfRemoteBizAppApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.addapp(appNewForm);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int delAppSync(Long appid) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizAppApi api = new DisconfRemoteBizAppApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.delapp(appid);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int addAreaSync(Area a) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizAreaApi api = new DisconfRemoteBizAreaApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.addArea(a);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int delAreaSync(Long id) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizAreaApi api = new DisconfRemoteBizAreaApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.delArea(id);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int addItemSync(ConfNewItemForm confNewForm) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.addItem(confNewForm);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileSync(ConfNewForm confNewForm, byte[] bs,String name) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFile(confNewForm, bs,name);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileWithTextSync(ConfNewForm confNewForm, String fileContent, String fileName) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFileWithText(confNewForm, fileContent, fileName);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateItemSync(long configId, String value) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateItem(configId, value);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileSync(long configId, byte[] bs,String name) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFile(configId, bs,name);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileWithTextSync(long configId, String fileContent) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFileWithText(configId, fileContent);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int deleteConfigSync(long configId) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.deleteConfig(configId);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int notifyOneSync(Long configId) {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.notifyOne(configId);
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int notifySomeSync() {
		init();

		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.notifySome();
					if (b) {
						res++;
					}
					api.close();
				} else {
					log.error("remote host connect or login error @  " + area.getHostport());
				}
				// end
			}
		}
		return otherCount - res;
	}

}
