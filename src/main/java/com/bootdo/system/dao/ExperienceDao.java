package com.bootdo.system.dao;

import com.bootdo.system.domain.ExperienceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
@Mapper
public interface ExperienceDao {

	ExperienceDO get(Integer id);
	
	List<ExperienceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExperienceDO experience);
	
	int update(ExperienceDO experience);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
