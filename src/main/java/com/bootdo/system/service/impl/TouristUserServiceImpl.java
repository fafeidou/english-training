package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.TouristUserDao;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.TouristUserService;



@Service
public class TouristUserServiceImpl implements TouristUserService {
	@Autowired
	private TouristUserDao touristUserDao;
	
	@Override
	public TouristUserDO get(Integer id){
		return touristUserDao.get(id);
	}
	
	@Override
	public List<TouristUserDO> list(Map<String, Object> map){
		return touristUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return touristUserDao.count(map);
	}
	
	@Override
	public int save(TouristUserDO touristUser){
		return touristUserDao.save(touristUser);
	}
	
	@Override
	public int update(TouristUserDO touristUser){
		return touristUserDao.update(touristUser);
	}
	
	@Override
	public int remove(Integer id){
		return touristUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return touristUserDao.batchRemove(ids);
	}
	
}
