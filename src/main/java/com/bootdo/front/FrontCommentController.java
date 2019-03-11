package com.bootdo.front;

import com.bootdo.common.utils.R;
import com.bootdo.front.domain.CommentRequest;
import com.bootdo.system.domain.CommentDO;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-03-11 10:33
 */
@Controller
public class FrontCommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/front/comment/save")
    @ResponseBody
    public R save(CommentRequest commentRequest, HttpSession httpSession) {
        TouristUserDO touristUser = (TouristUserDO) httpSession.getAttribute("touristUser");

        CommentDO commentDO = new CommentDO();
        if (touristUser != null) {
            commentDO.setUserId(touristUser.getId());
        }
        commentDO.setComment(commentRequest.getCommentContent());
        commentDO.setIsShow(false);
        commentDO.setCourseId(commentRequest.getId());
        commentDO.setUpdateTime(new Date());
        commentDO.setCreateTime(new Date());
        commentService.save(commentDO);
        return R.ok();
    }
}
