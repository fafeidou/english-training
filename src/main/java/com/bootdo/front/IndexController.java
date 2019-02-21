package com.bootdo.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-21 17:33
 */
@Controller
public class IndexController {
    @GetMapping("/front")
    public String index() {
        return "front/index";
    }

    @GetMapping("/courses")
    public String courses() {
        return "front/courses";
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
