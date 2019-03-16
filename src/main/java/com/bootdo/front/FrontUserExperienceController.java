package com.bootdo.front;

import com.bootdo.common.utils.R;
import com.bootdo.system.domain.ExperienceDO;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
}
