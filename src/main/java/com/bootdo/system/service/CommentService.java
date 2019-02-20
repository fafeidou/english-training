package com.bootdo.system.service;

import com.bootdo.system.domain.CommentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
public interface CommentService {
	
	CommentDO get(Integer id);
	
	List<CommentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommentDO comment);
	
	int update(CommentDO comment);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
