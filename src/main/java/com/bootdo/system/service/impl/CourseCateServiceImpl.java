package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.CourseCateDao;
import com.bootdo.system.domain.CourseCateDO;
import com.bootdo.system.service.CourseCateService;



@Service
public class CourseCateServiceImpl implements CourseCateService {
	@Autowired
	private CourseCateDao courseCateDao;
	
	@Override
	public CourseCateDO get(Integer id){
		return courseCateDao.get(id);
	}
	
	@Override
	public List<CourseCateDO> list(Map<String, Object> map){
		return courseCateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseCateDao.count(map);
	}
	
	@Override
	public int save(CourseCateDO courseCate){
		return courseCateDao.save(courseCate);
	}
	
	@Override
	public int update(CourseCateDO courseCate){
		return courseCateDao.update(courseCate);
	}
	
	@Override
	public int remove(Integer id){
		return courseCateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return courseCateDao.batchRemove(ids);
	}
	
}
