package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.CourseDao;
import com.bootdo.system.domain.CourseDO;
import com.bootdo.system.service.CourseService;



@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public CourseDO get(Integer id){
		return courseDao.get(id);
	}
	
	@Override
	public List<CourseDO> list(Map<String, Object> map){
		return courseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseDao.count(map);
	}
	
	@Override
	public int save(CourseDO course){
		return courseDao.save(course);
	}
	
	@Override
	public int update(CourseDO course){
		return courseDao.update(course);
	}
	
	@Override
	public int remove(Integer id){
		return courseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return courseDao.batchRemove(ids);
	}
	
}
