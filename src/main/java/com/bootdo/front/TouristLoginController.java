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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("tourist/register")
    String register() {
        return "front/register";
    }

    @Log("游客登录")
    @PostMapping("tourist/register")
    @ResponseBody
    R register(String username, String password, String phone, String email, String verify, HttpServletRequest request, HttpSession httpSession) {
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
            return R.error("用户名已存在");
        }
        TouristUserDO touristUserDO = new TouristUserDO();
        touristUserDO.setUsername(username);
        touristUserDO.setPassword(MD5Utils.encrypt(username, password));
        touristUserDO.setEmail(email);
        if (StringUtils.isNotBlank(phone)) {
            touristUserDO.setPhone(phone);
        }
        touristUserDO.setCreateTime(new Date());
        touristUserDO.setUpdateTime(new Date());
        touristUserService.save(touristUserDO);
        return R.ok("注册成功");
    }

    @GetMapping("tourist/manage")
    String manage() {
        return "index_v2";
    }

    @GetMapping("tourist/personal")
    String personal(ModelMap modelMap, HttpSession httpSession) {
        TouristUserDO touristUser = (TouristUserDO) httpSession.getAttribute("touristUser");
        if (touristUser != null) {
            modelMap.put("touristUser", touristUserService.get(touristUser.getId()));
            return "/system/touristUser/personal";
        }
        return "redirect:/front";
    }

    @PostMapping("tourist/personal/update")
    @ResponseBody
    R update(TouristUserDO userDO, HttpSession httpSession) {
        touristUserService.update(userDO);
        httpSession.setAttribute("touristUser", touristUserService.get(userDO.getId()));
        return R.ok();
    }

    @PostMapping("tourist/personal/resetPwd")
    @ResponseBody
    R resetPwd(TouristUserDO userDO, HttpSession httpSession) {
        if (!userDO.getConfirm_password().equals(userDO.getPwdNew())) {
            return R.error("两次密码不一致");
        }
        TouristUserDO touristUserDO = touristUserService.get(userDO.getId());
        if (touristUserDO == null) {
            return R.error("用户不存在");
        }
        if (!touristUserDO.getPassword().equals(MD5Utils.encrypt(touristUserDO.getUsername(), userDO.getPwdOld()))) {
            return R.error("密码错误");
        }
        userDO.setPassword(MD5Utils.encrypt(touristUserDO.getUsername(), userDO.getConfirm_password()));
        touristUserService.update(userDO);
        httpSession.setAttribute("touristUser", touristUserService.get(userDO.getId()));
        return R.ok();
    }

    @ResponseBody
    @PostMapping("tourist/personal/uploadImg")
    R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpSession httpSession) {
        TouristUserDO touristUser = (TouristUserDO) httpSession.getAttribute("touristUser");
        try {
            TouristUserDO userDO = touristUserService.updatePersonalImg(file, avatar_data, touristUser.getId());
            httpSession.setAttribute("touristUser", userDO);
            Map<String, Object> result = new HashMap<>();
            result.put("url", userDO.getHeadUrl());
            return R.ok(result);
        } catch (Exception e) {
            return R.error("更新图像失败！");
        }
    }
}
