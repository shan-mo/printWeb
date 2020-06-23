package com.example.paint;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Test {

    @RequestMapping("/getTime")
    @ResponseBody
    public String getTime() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss");
        return s.format(new Date());
    }

    @RequestMapping("/thy")
    public String getText(Map<String, String> map) {
        map.put("hello", "hello world");
        return "thyTest";
    }

    @RequestMapping("/flow")
    @ResponseBody
    public List flow(@RequestParam("page") String page) {
        System.out.println(page);
        int i = Integer.parseInt(page);
        List l = new ArrayList();
        l.add(5);
        l.add(i + 1);
        l.add(i + 2);
        l.add(i + 3);
        l.add(i + 4);
        l.add(i + 5);
        return l;
    }
}
