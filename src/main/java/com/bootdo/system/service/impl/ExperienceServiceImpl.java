package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.ExperienceDao;
import com.bootdo.system.domain.ExperienceDO;
import com.bootdo.system.service.ExperienceService;



@Service
public class ExperienceServiceImpl implements ExperienceService {
	@Autowired
	private ExperienceDao experienceDao;
	
	@Override
	public ExperienceDO get(Integer id){
		return experienceDao.get(id);
	}
	
	@Override
	public List<ExperienceDO> list(Map<String, Object> map){
		return experienceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return experienceDao.count(map);
	}
	
	@Override
	public int save(ExperienceDO experience){
		return experienceDao.save(experience);
	}
	
	@Override
	public int update(ExperienceDO experience){
		return experienceDao.update(experience);
	}
	
	@Override
	public int remove(Integer id){
		return experienceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return experienceDao.batchRemove(ids);
	}
	
}
