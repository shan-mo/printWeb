package com.paint.controller;

import com.alibaba.fastjson.JSON;
import com.paint.pojo.po.Invitation;
import com.paint.service.invitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 有关帖子的controller都在这里
 */
@Controller
public class invitationController {

    @Autowired
    private invitationService invitationService;

    /**
     * 发布帖子
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/publishinvation", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publish(@RequestParam(value = "file[]", required = false) MultipartFile[] files, @RequestParam(value = "title") String title, @RequestParam(value = "textarea") String textarea) {
        Invitation invitation = new Invitation(title, textarea);
        return JSON.toJSONString(invitationService.publishInvitation(invitation, files));
    }

}
