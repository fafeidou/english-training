package com.bootdo.system.dao;

import com.bootdo.system.domain.TouristUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-24 17:02:25
 */
@Mapper
public interface TouristUserDao {

	TouristUserDO get(Integer id);
	
	List<TouristUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TouristUserDO touristUser);
	
	int update(TouristUserDO touristUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
