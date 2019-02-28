package com.bootdo.front;

import com.bootdo.system.domain.NavigationDO;
import com.bootdo.system.service.NavigationService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-21 17:33
 */
@Controller
public class IndexController {
    @Autowired
    private NavigationService navigationService;

    @GetMapping("/front")
    public String index(ModelMap modelMap) {
        List<NavigationDO> navigations = navigationService.list(Maps.newHashMap());
        modelMap.addAttribute("navigations", navigations);
        return "front/index";
    }

    @GetMapping("/teachers")
    public String teachers() {
        return "front/teachers";
    }

    @GetMapping("/about")
    public String about() {
        return "front/about";
    }

    @GetMapping("/xingyouhui")
    public String xingyouhui() {
        return "front/xingyouhui";
    }
}
