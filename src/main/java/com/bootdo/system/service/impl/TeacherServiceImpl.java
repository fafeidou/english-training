package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.TeacherDao;
import com.bootdo.system.domain.TeacherDO;
import com.bootdo.system.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public TeacherDO get(Integer id){
		return teacherDao.get(id);
	}
	
	@Override
	public List<TeacherDO> list(Map<String, Object> map){
		return teacherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teacherDao.count(map);
	}
	
	@Override
	public int save(TeacherDO teacher){
		return teacherDao.save(teacher);
	}
	
	@Override
	public int update(TeacherDO teacher){
		return teacherDao.update(teacher);
	}
	
	@Override
	public int remove(Integer id){
		return teacherDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return teacherDao.batchRemove(ids);
	}
	
}
