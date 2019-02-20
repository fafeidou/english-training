package com.bootdo.system.service;

import com.bootdo.system.domain.TeacherDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 13:58:31
 */
public interface TeacherService {
	
	TeacherDO get(Integer id);
	
	List<TeacherDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeacherDO teacher);
	
	int update(TeacherDO teacher);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
