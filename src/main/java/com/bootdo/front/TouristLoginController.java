package com.bootdo.front;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.RandomValidateCodeUtil;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.TouristUserService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-25 13:16
 */
@Controller
public class TouristLoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TouristUserService touristUserService;

    @GetMapping("tourist/login")
    String login() {
        return "front/login";
    }

    @Log("游客登录")
    @PostMapping("tourist/login")
    @ResponseBody
    R ajaxLogin(String username, String password, String verify, HttpServletRequest request, HttpSession httpSession) {
        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return R.error("请输入验证码");
            }
            if (random.equals(verify)) {
            } else {
                return R.error("请输入正确的验证码");
            }
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return R.error("验证码校验失败");
        }
        HashMap<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("username", username);
        List<TouristUserDO> result = touristUserService.list(paramsMap);
        if (CollectionUtils.isNotEmpty(result)) {
            TouristUserDO touristUserDO = result.get(0);
            if (!touristUserDO.getPassword().equals(MD5Utils.encrypt(username, password))) {
                return R.error("用户或密码错误");
            }
            httpSession.setAttribute("touristUser", touristUserDO);
        }
        return R.ok();
    }

    @GetMapping("tourist/loginOut")
    String loginOut(HttpSession httpSession) {
        httpSession.removeAttribute("touristUser");
        return "redirect:/front";
    }
}
