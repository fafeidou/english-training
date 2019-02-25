package com.bootdo.front;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.CourseCateDO;
import com.bootdo.system.domain.CourseDO;
import com.bootdo.system.domain.TeacherDO;
import com.bootdo.system.service.CourseCateService;
import com.bootdo.system.service.CourseService;
import com.bootdo.system.service.TeacherService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-22 9:40
 */
@Controller
public class FrontCourseController {
    @Autowired
    private CourseCateService courseCateService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/courses")
    public String courses(@RequestParam Map<String, Object> params, ModelMap modelMap) {
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
        modelMap.addAttribute("pageUtils", pageUtils);
        return "front/courses";
    }

    @GetMapping("/courses/details")
    public String details(@RequestParam Map<String, Object> params, ModelMap modelMap) {
        Object courseId = params.get("courseId");
        CourseDO courseDO = courseService.get(Integer.parseInt(courseId.toString()));
        TeacherDO teacherDO = teacherService.get(courseDO.getTeacherId());
        if (teacherDO != null) {
            courseDO.setTeacherName(teacherDO.getUsername());
        }
        CourseCateDO courseCateDO = courseCateService.get(courseDO.getTrainCorseId());
        if (courseCateDO != null) {
            courseDO.setTrainCorseCateName(courseCateDO.getName());
        }
        modelMap.put("courseDO", courseDO);
        return "front/course_detail";
    }
}
