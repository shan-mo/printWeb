package com.paint.controller;

import com.alibaba.fastjson.JSON;
import com.paint.constants.ResultCode;
import com.paint.pojo.bo.Result;
import com.paint.pojo.po.User;
import com.google.code.kaptcha.Constants;
import com.paint.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户相关的Controller
 */
@Controller
public class userController {
    @Autowired
    private userService userService;

    private final Logger logger = LoggerFactory.getLogger(userController.class);

    /**
     * 登录
     *
     * @param request
     * @param username
     * @param password
     * @param aptcha
     * @return
     */
    @RequestMapping(value = "login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password, String aptcha) {
        Result result = new Result();
        User user = null;
        HttpSession session = request.getSession();
        String codetext = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (aptcha.equals(codetext)) {
            user = new User();
            user.setEmail(username);
            user.setPassWord(password);
            result = userService.login(user);
            if (result.getResultCode().equals(ResultCode.SUCCESS_CODE)) {
                request.getSession().setAttribute("user", result.getResult());
            } else {
                logger.info("[" + result.getResultCode() + "]" + result.getResultMessage());
            }
        } else {
            result.setResultCode(ResultCode.ERROR_CODE);
            result.setResultMessage(ResultCode.APTCHA_ERR_MSG);
            logger.info("[" + result.getResultCode() + "]" + result.getResultMessage());
        }
        return JSON.toJSONString(result);
    }

    /**
     * 注册
     *
     * @param mininame
     * @param email
     * @param passwd
     * @return
     */
    @RequestMapping(value = "/regist", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String regist(String mininame, String email, String passwd) {
        User user = new User(mininame, email, passwd);
        Result result = userService.regist(user);
        return JSON.toJSONString(result);
    }

    /**
     * 登出
     *
     * @param user
     * @return
     */
    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request, User user) {
        request.getSession().removeAttribute(user.getEmail());
        return "initlogin";
    }

    /**
     * 用户注销账号
     *
     * @param user
     * @return
     */
    @RequestMapping("userCancle")
    public String userCancle(User user) {
        return "";
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("userMessageUpdate")
    public String userMessageUpdate(User user) {
        return "";
    }
}
