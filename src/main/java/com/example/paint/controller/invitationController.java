package com.example.paint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 有关帖子的controller都在这里
 */
@Controller
public class invitationController {

    /**
     * 发布帖子
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping("/publishinvation")
    @ResponseBody
    public String publish(@RequestParam(value = "file[]", required = false) MultipartFile[] files, HttpServletRequest request) {
        System.out.println(files.length);
        String filepath = "G:/";
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                System.out.println(fileName);
                File dest = new File(filepath + fileName);
                file.transferTo(dest);
            }
            return "success";
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "error";
    }

}
