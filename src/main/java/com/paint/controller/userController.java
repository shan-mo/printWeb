package com.paint.controller;

import com.paint.constants.ResultCode;
import com.paint.pojo.po.User;
import com.google.code.kaptcha.Constants;
import com.paint.service.userService;
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


    /**
     * 登录
     *
     * @param request
     * @param username
     * @param password
     * @param aptcha
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password, String aptcha) {
        HttpSession session = request.getSession();
        String codetext = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (aptcha.equals(codetext)) {
            User user = new User();
            user.setEmail(username);
            user.setPassWord(password);
            if (userService.login(user).getResultCode().equals(ResultCode.USER_FOUND)) {
                request.getSession().setAttribute("user", "success");
                return "success";
            }
        }
        return "fair";
    }

    /**
     * 注册
     *
     * @param mininame
     * @param email
     * @param passwd
     * @param repasswd
     * @return
     */
    @RequestMapping("/regist")
    @ResponseBody
    public String regist(String mininame, String email, String passwd, String repasswd) {
        System.out.println(mininame);
        System.out.println(email);
        System.out.println(passwd);
        System.out.println(repasswd);

        return "success";
    }

    /**
     * 登出
     *
     * @param user
     * @return
     */
    @RequestMapping("loginout")
    public String loginout(User user) {
        return "";
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
