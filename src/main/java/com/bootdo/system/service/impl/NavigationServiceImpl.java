package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.NavigationDao;
import com.bootdo.system.domain.NavigationDO;
import com.bootdo.system.service.NavigationService;



@Service
public class NavigationServiceImpl implements NavigationService {
	@Autowired
	private NavigationDao navigationDao;
	
	@Override
	public NavigationDO get(Integer id){
		return navigationDao.get(id);
	}
	
	@Override
	public List<NavigationDO> list(Map<String, Object> map){
		return navigationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return navigationDao.count(map);
	}
	
	@Override
	public int save(NavigationDO navigation){
		return navigationDao.save(navigation);
	}
	
	@Override
	public int update(NavigationDO navigation){
		return navigationDao.update(navigation);
	}
	
	@Override
	public int remove(Integer id){
		return navigationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return navigationDao.batchRemove(ids);
	}
	
}
