package com.example.paint.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 这个控制器主要控制那些需要页面跳转的controller
 * 和一些工具的contorller，如验证码等
 */
@Controller
public class UtilController {
    @Autowired
    private Producer captchaproducer;

    private Logger logger = LoggerFactory.getLogger(UtilController.class);

    /**
     * 跳转到登录页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/initLogin")
    public String gotoLogin(HttpServletRequest request) {
        logger.info("初始化登录页面");
        return "login";
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/gotoregist")
    public String gotoregist() {
        return "regist";
    }

    /**
     * 跳转到论坛主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String gotoindex() {
        return "index";
    }

    /**
     * 跳转到用户中心
     *
     * @return
     */
    @RequestMapping("/userCenter")
    public String gotoUserCenter() {
        return "userCenter";
    }

    /**
     * 跳转到帖子页面
     *
     * @return
     */
    @RequestMapping("/invitation")
    public String invitationPage() {
        return "invitationWindow";
    }

    /**
     * 跳转到发布帖子页面
     *
     * @return
     */
    @RequestMapping("/publisInvitation")
    public String publishInvitation() {
        return "publishWindow";

    }

    /**
     * 跳转到评论页面
     *
     * @return
     */
    @RequestMapping("/comment")
    public String commmentPage() {
        System.out.println("评论页面");
        return "commentWindow";
    }


    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getcha")
    public ModelAndView getcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaproducer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaproducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
}
