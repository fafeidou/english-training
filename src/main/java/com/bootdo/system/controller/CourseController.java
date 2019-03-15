package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.CourseCateDO;
import com.bootdo.system.domain.CourseDO;
import com.bootdo.system.domain.TeacherDO;
import com.bootdo.system.service.CourseCateService;
import com.bootdo.system.service.CourseService;
import com.bootdo.system.service.TeacherService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/system/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCateService courseCateService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping()
    @RequiresPermissions("system:course:course")
    String Course() {
        return "system/course/course";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:course:course")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CourseDO> courseList = courseService.list(query);
        if (CollectionUtils.isNotEmpty(courseList)) {
            courseList.forEach(i -> {
                TeacherDO teacherDO = teacherService.get(i.getTeacherId());
                if (teacherDO != null) {
                    i.setTeacherName(teacherDO.getUsername());
                }
                CourseCateDO courseCateDO = courseCateService.get(i.getTrainCorseId());
                if (courseCateDO != null) {
                    i.setTrainCorseCateName(courseCateDO.getName());
                }
            });

        }
        int total = courseService.count(query);
        PageUtils pageUtils = new PageUtils(courseList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:course:add")
    String add(ModelMap modelMap) {
        List<CourseCateDO> courseCates = courseCateService.list(Maps.newHashMap());
        List<TeacherDO> teachers = teacherService.list(Maps.newHashMap());
        modelMap.put("courseCates", courseCates);
        modelMap.put("teachers", teachers);
        return "system/course/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:course:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        CourseDO course = courseService.get(id);
        model.addAttribute("course", course);
        return "system/course/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:course:add")
    public R save(CourseDO course) {
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        if (courseService.save(course) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:course:edit")
    public R update(CourseDO course) {
        if (course.getIsFree() == null) {
            course.setIsFree(false);
        }
        course.setUpdateTime(new Date());
        courseService.update(course);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:course:remove")
    public R remove(Integer id) {
        if (courseService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:course:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        courseService.batchRemove(ids);
        return R.ok();
    }

}
