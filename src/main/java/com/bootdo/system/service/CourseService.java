package com.bootdo.system.service;

import com.bootdo.system.domain.CourseDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
public interface CourseService {
	
	CourseDO get(Integer id);
	
	List<CourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
