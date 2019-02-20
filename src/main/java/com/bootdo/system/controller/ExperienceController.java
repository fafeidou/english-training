package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.ExperienceDO;
import com.bootdo.system.service.ExperienceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */

@Controller
@RequestMapping("/system/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @GetMapping()
    @RequiresPermissions("system:experience:experience")
    String Experience() {
        return "system/experience/experience";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:experience:experience")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ExperienceDO> experienceList = experienceService.list(query);
        int total = experienceService.count(query);
        PageUtils pageUtils = new PageUtils(experienceList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:experience:add")
    String add() {
        return "system/experience/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:experience:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        ExperienceDO experience = experienceService.get(id);
        model.addAttribute("experience", experience);
        return "system/experience/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:experience:add")
    public R save(ExperienceDO experience) {
        experience.setCreateTime(new Date());
        experience.setUpdateTime(new Date());
        if (experienceService.save(experience) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:experience:edit")
    public R update(ExperienceDO experience) {
        experience.setUpdateTime(new Date());
        experienceService.update(experience);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:experience:remove")
    public R remove(Integer id) {
        if (experienceService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:experience:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        experienceService.batchRemove(ids);
        return R.ok();
    }

}
