package com.bootdo.front;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private CommentService commentService;
    @Autowired
    private TouristUserService touristUserService;

    /**
     * 体验课程
     *
     * @param params
     * @param modelMap
     * @return
     */
    @GetMapping("/courses")
    public String courses(@RequestParam Map<String, Object> params, ModelMap modelMap) {
        //查询列表数据
        Query query = new Query(params);
        query.put("isFree", "1");
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

    /**
     * 不是体验课程
     *
     * @param params
     * @param modelMap
     * @return
     */
    @GetMapping("/notFreeCourses")
    public String notFreeCourses(@RequestParam Map<String, Object> params, ModelMap modelMap) {
        //查询列表数据
        Query query = new Query(params);
        query.put("isFree", "0");
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
        return "front/notFreeCourses";
    }

    @GetMapping("/notFreeCourses/details")
    public String notFreeCoursesDetails(@RequestParam Map<String, Object> params, ModelMap modelMap) {
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
        return "front/not_free_course_detail";
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
        //推荐课程 查出种类下的点击数最多前十条不包括当前数据
        Map<String, Object> map = new HashMap<>();
        map.put("trainCorseId", courseDO.getTrainCorseId());
        map.put("offset", 0);
        map.put("limit", 5);
        map.put("sort", "click_count");
        map.put("order", "desc");
        List<CourseDO> recommendList = courseService.list(map);
        if (CollectionUtils.isNotEmpty(recommendList)) {
            recommendList = recommendList.stream().filter(i -> !i.getId().equals(courseDO.getId())).collect(Collectors.toList());
            recommendList.forEach(i -> {
                TeacherDO teacherDO1 = teacherService.get(i.getTeacherId());
                i.setTeacherName(teacherDO1.getUsername());
            });
            modelMap.put("recommendList", recommendList);
        }

        modelMap.put("courseDO", courseDO);
        //查出所有的评论
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("courseId", courseDO.getId());
        commentMap.put("isShow", 1);
        List<CommentDO> commentDOList = commentService.list(commentMap);
        if (CollectionUtils.isNotEmpty(commentDOList)) {
            commentDOList.forEach(i -> {
                //查出用户
                TouristUserDO touristUserDO = touristUserService.get(i.getUserId());
                if (touristUserDO != null && touristUserDO.getUsername() != null) {
                    i.setUserName(touristUserDO.getUsername());
                }
            });
        } else {
            commentDOList = Lists.newArrayList();
        }

        modelMap.put("commentDOList", commentDOList);
        return "front/course_detail";
    }
}
