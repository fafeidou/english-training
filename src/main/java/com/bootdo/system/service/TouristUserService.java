package com.bootdo.system.service;

import com.bootdo.system.domain.TouristUserDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-24 17:02:25
 */
public interface TouristUserService {
	
	TouristUserDO get(Integer id);
	
	List<TouristUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TouristUserDO touristUser);
	
	int update(TouristUserDO touristUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	TouristUserDO updatePersonalImg(MultipartFile file, String avatar_data, Integer id) throws Exception;
}
