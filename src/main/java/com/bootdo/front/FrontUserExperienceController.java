package com.bootdo.front;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.CourseCateService;
import com.bootdo.system.service.CourseService;
import com.bootdo.system.service.ExperienceService;
import com.bootdo.system.service.TeacherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-03-16 10:11
 */
@Controller
public class FrontUserExperienceController {
    @Autowired
    ExperienceService experienceService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseCateService courseCateService;
    @Autowired
    TeacherService teacherService;

    @ResponseBody
    @GetMapping("/front/experience/save")
    public R save(String trainCorseId, HttpSession httpSession) {
        if (trainCorseId == null) {
            return R.error("请选择课程");
        }

        TouristUserDO touristUser = (TouristUserDO) httpSession.getAttribute("touristUser");
        if (touristUser == null) {
            return R.error("请先登录");
        }
        ExperienceDO experienceDO = new ExperienceDO();
        experienceDO.setUserId(touristUser.getId());
        experienceDO.setTrainCorseId(Integer.parseInt(trainCorseId));
        experienceDO.setCreateTime(new Date());
        experienceDO.setUpdateTime(new Date());
        if (experienceService.save(experienceDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/front/experience/list")
    public String list(ModelMap modelMap) {
        return "front/experience/experience";
    }

    @ResponseBody
    @GetMapping("/front/experience/data")
    public PageUtils list(@RequestParam Map<String, Object> params, HttpSession httpSession) {
        TouristUserDO touristUser = (TouristUserDO) httpSession.getAttribute("touristUser");
        if (touristUser == null) {
            throw new RuntimeException("请先登录!!!");
        }
        params.put("userId", touristUser.getId());
        //查询列表数据
        Query query = new Query(params);
        List<ExperienceDO> experienceList = experienceService.list(query);
        if (CollectionUtils.isNotEmpty(experienceList)) {
            experienceList.stream().forEach(i -> {
                CourseDO courseDO = courseService.get(i.getTrainCorseId());
                if (courseDO != null) {
                    i.setCourseName(courseDO.getName());
                    CourseCateDO courseCateDO = courseCateService.get(courseDO.getTrainCorseId());
                    if (courseCateDO != null) {
                        i.setCourseCateName(courseCateDO.getName());
                    }
                    TeacherDO teacherDO = teacherService.get(courseDO.getTeacherId());
                    if (teacherDO != null) {
                        i.setTeacherName(teacherDO.getUsername());
                    }
                }
            });
        }
        int total = experienceService.count(query);
        PageUtils pageUtils = new PageUtils(experienceList, total);
        return pageUtils;
    }
}
