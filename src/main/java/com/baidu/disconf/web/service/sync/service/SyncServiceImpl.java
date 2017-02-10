package com.baidu.disconf.web.service.sync.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.disconf.web.service.app.form.AppNewForm;
import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.disconf.web.service.area.dao.AreaDao;
import com.baidu.disconf.web.service.config.form.ConfNewForm;
import com.baidu.disconf.web.service.config.form.ConfNewItemForm;
import com.baidu.wonder.other.DisconfRemoteBizAppApi;
import com.baidu.wonder.other.DisconfRemoteBizAreaApi;
import com.baidu.wonder.other.DisconfRemoteBizItemApi;
import com.baidu.wonder.other.PropUtils;

@Service
public class SyncServiceImpl implements SyncService {
	public static Logger log = LoggerFactory.getLogger(SyncServiceImpl.class);
	@Autowired
	private AreaDao areaDao;

	private String areaid = PropUtils.getKey("localArea");
	private Long area_id = Long.parseLong(areaid);
	private List<Area> areas = areaDao.findAll();
	private int otherCount = areas.size() - 1;

	@Override
	public int addApp(AppNewForm appNewForm) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int delApp(Long appid) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int addArea(Area a) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int delArea(Long id) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int addItem(ConfNewItemForm confNewForm) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFile(ConfNewForm confNewForm, MultipartFile file) {
		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFile(confNewForm, file);
					if (b) {
						res++;
					}
					api.close();
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileWithText(ConfNewForm confNewForm, String fileContent, String fileName) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateItem(long configId, String value) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFile(long configId, MultipartFile file) {
		int res = 0;
		for (Area area : areas) {
			if (area.getId() != area_id) {
				// start
				DisconfRemoteBizItemApi api = new DisconfRemoteBizItemApi(area.getHostport());
				if (api.session() || api.login(area.getName(), area.getPassword())) {
					boolean b = api.updateFile(configId, file);
					if (b) {
						res++;
					}
					api.close();
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int updateFileWithText(long configId, String fileContent) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int deleteConfig(long configId) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int notifyOne(Long configId) {
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
				}
				// end
			}
		}
		return otherCount - res;
	}

	@Override
	public int notifySome() {
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
				}
				// end
			}
		}
		return otherCount - res;
	}
	
}
