package com.paint.controller;

import com.alibaba.fastjson.JSON;
import com.paint.pojo.bo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.User;
import com.paint.service.invitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public String publish(HttpServletRequest request, @RequestParam(value = "file[]", required = false) MultipartFile[] files, @RequestParam(value = "title") String title, @RequestParam(value = "textarea") String textarea) {
        Invitation invitation = new Invitation(title, textarea);
        User user = (User) request.getSession().getAttribute("user");
        return JSON.toJSONString(invitationService.publishInvitation(user,invitation, files));
    }

    /**
     * 分页获取帖子列表
     *
     * @param page
     * @param firstcreateDate
     * @return
     */
    @RequestMapping(value = "/getinvitationlist", produces = "application/json ; charset=utf-8")
    @ResponseBody
    public String getInvitationList(@RequestParam("page") String page, @RequestParam("createDate") String firstcreateDate) {
        Result result = new Result();
        invitationService.getInvitationList(result, page, firstcreateDate);
        return JSON.toJSONString(result);
    }

    /**
     * 根据帖子id获取具体的帖子内容显示在帖子明细弹框中
     *
     * @return
     */
    @RequestMapping(value = "/getinvitation", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getInvitation(@RequestParam("id") String invitationId) {
        Result result = new Result();
        invitationService.getInvitation(result, Integer.parseInt(invitationId));
        return JSON.toJSONString(result);
    }

}
