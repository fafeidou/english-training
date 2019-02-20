package com.bootdo.system.service;

import com.bootdo.system.domain.CourseCateDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
public interface CourseCateService {
	
	CourseCateDO get(Integer id);
	
	List<CourseCateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseCateDO courseCate);
	
	int update(CourseCateDO courseCate);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
