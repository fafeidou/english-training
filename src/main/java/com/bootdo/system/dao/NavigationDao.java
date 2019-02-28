package com.bootdo.system.dao;

import com.bootdo.system.domain.NavigationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-28 09:11:34
 */
@Mapper
public interface NavigationDao {

	NavigationDO get(Integer id);
	
	List<NavigationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NavigationDO navigation);
	
	int update(NavigationDO navigation);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
