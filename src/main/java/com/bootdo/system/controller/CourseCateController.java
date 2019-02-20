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

import com.bootdo.system.domain.CourseCateDO;
import com.bootdo.system.service.CourseCateService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
 
@Controller
@RequestMapping("/system/courseCate")
public class CourseCateController {
	@Autowired
	private CourseCateService courseCateService;
	
	@GetMapping()
	@RequiresPermissions("system:courseCate:courseCate")
	String CourseCate(){
	    return "system/courseCate/courseCate";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:courseCate:courseCate")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseCateDO> courseCateList = courseCateService.list(query);
		int total = courseCateService.count(query);
		PageUtils pageUtils = new PageUtils(courseCateList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:courseCate:add")
	String add(){
	    return "system/courseCate/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:courseCate:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CourseCateDO courseCate = courseCateService.get(id);
		model.addAttribute("courseCate", courseCate);
	    return "system/courseCate/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:courseCate:add")
	public R save( CourseCateDO courseCate){
		courseCate.setCreateTime(new Date());
		courseCate.setUpdateTime(new Date());
		if(courseCateService.save(courseCate)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:courseCate:edit")
	public R update( CourseCateDO courseCate){
		courseCate.setUpdateTime(new Date());
		courseCateService.update(courseCate);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:courseCate:remove")
	public R remove( Integer id){
		if(courseCateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:courseCate:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		courseCateService.batchRemove(ids);
		return R.ok();
	}
	
}
