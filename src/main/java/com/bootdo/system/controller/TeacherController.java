package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.TeacherDO;
import com.bootdo.system.service.TeacherService;
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
 * @date 2019-02-20 13:58:31
 */

@Controller
@RequestMapping("/system/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping()
    @RequiresPermissions("system:teacher:teacher")
    String Teacher() {
        return "system/teacher/teacher";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:teacher:teacher")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TeacherDO> teacherList = teacherService.list(query);
        int total = teacherService.count(query);
        PageUtils pageUtils = new PageUtils(teacherList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:teacher:add")
    String add() {
        return "system/teacher/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:teacher:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        TeacherDO teacher = teacherService.get(id);
        model.addAttribute("teacher", teacher);
        return "system/teacher/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:teacher:add")
    public R save(TeacherDO teacher) {
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(new Date());
        if (teacherService.save(teacher) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:teacher:edit")
    public R update(TeacherDO teacher) {
        teacher.setUpdateTime(new Date());
        teacherService.update(teacher);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:teacher:remove")
    public R remove(Integer id) {
        if (teacherService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:teacher:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        teacherService.batchRemove(ids);
        return R.ok();
    }

}
