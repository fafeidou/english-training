package com.bootdo.system.service;

import com.bootdo.system.domain.ExperienceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
public interface ExperienceService {
	
	ExperienceDO get(Integer id);
	
	List<ExperienceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExperienceDO experience);
	
	int update(ExperienceDO experience);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
