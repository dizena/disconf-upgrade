package com.baidu.disconf.web.service.area.dao;

import com.baidu.disconf.web.service.area.bo.Area;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;

public interface AreaDao  extends BaseDao<Long, Area> {
	 /**
     * @param 
     *
     * @return int
     */
    int getCount();
}
