package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.TouristUserService;
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
 * @date 2019-02-24 17:02:25
 */

@Controller
@RequestMapping("/system/touristUser")
public class TouristUserController {
    @Autowired
    private TouristUserService touristUserService;

    @GetMapping()
    @RequiresPermissions("system:touristUser:touristUser")
    String TouristUser() {
        return "system/touristUser/touristUser";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:touristUser:touristUser")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TouristUserDO> touristUserList = touristUserService.list(query);
        int total = touristUserService.count(query);
        PageUtils pageUtils = new PageUtils(touristUserList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:touristUser:add")
    String add() {
        return "system/touristUser/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:touristUser:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        TouristUserDO touristUser = touristUserService.get(id);
        model.addAttribute("touristUser", touristUser);
        return "system/touristUser/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:touristUser:add")
    public R save(TouristUserDO touristUser) {
        touristUser.setCreateTime(new Date());
        touristUser.setUpdateTime(new Date());
        if (touristUserService.save(touristUser) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:touristUser:edit")
    public R update(TouristUserDO touristUser) {
        touristUser.setUpdateTime(new Date());
        touristUserService.update(touristUser);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:touristUser:remove")
    public R remove(Integer id) {
        if (touristUserService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:touristUser:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        touristUserService.batchRemove(ids);
        return R.ok();
    }

}
