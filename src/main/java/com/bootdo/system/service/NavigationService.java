package com.bootdo.system.service;

import com.bootdo.system.domain.NavigationDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-28 09:11:34
 */
public interface NavigationService {
	
	NavigationDO get(Integer id);
	
	List<NavigationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NavigationDO navigation);
	
	int update(NavigationDO navigation);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
