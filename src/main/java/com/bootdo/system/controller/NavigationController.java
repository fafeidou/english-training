package com.bootdo.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.NavigationDO;
import com.bootdo.system.service.NavigationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-28 09:11:34
 */
 
@Controller
@RequestMapping("/system/navigation")
public class NavigationController {
	@Autowired
	private NavigationService navigationService;
	
	@GetMapping()
	@RequiresPermissions("system:navigation:navigation")
	String Navigation(){
	    return "system/navigation/navigation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:navigation:navigation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NavigationDO> navigationList = navigationService.list(query);
		int total = navigationService.count(query);
		PageUtils pageUtils = new PageUtils(navigationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:navigation:add")
	String add(){
	    return "system/navigation/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:navigation:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NavigationDO navigation = navigationService.get(id);
		model.addAttribute("navigation", navigation);
	    return "system/navigation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:navigation:add")
	public R save( NavigationDO navigation){
		navigation.setCreateTime(new Date());
		navigation.setUpdateTime(new Date());
		if(navigationService.save(navigation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:navigation:edit")
	public R update( NavigationDO navigation){
		navigation.setUpdateTime(new Date());
		navigationService.update(navigation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:navigation:remove")
	public R remove( Integer id){
		if(navigationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:navigation:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		navigationService.batchRemove(ids);
		return R.ok();
	}
	
}
