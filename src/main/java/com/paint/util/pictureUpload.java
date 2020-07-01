package com.paint.util;

import com.paint.constants.Constants;
import com.paint.constants.ResultCode;
import com.paint.pojo.Result;
import com.paint.pojo.po.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pictureUpload {
    /**
     * 将用户上传的图片转换成list格式，方便数据库插入
     *
     * @param pictures
     * @return
     */
    public static List<Picture> getPictureList(MultipartFile[] pictures) {
        Picture p = null;
        List<Picture> pictureList = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            String fileName = picture.getOriginalFilename().replaceAll(" ", "").trim();
            p = new Picture(fileName, Constants.PICTURE_INVITATION);
            pictureList.add(p);
        }
        return pictureList;
    }

    /**
     * 该方法将图片保存在磁盘中
     * 图片名称需要修改
     * 格式: 帖子id + 时间串 + 原文件名
     *
     * @param result
     * @param pictures
     * @param url
     * @return
     */
    public static Result upload(Result result, MultipartFile[] pictures, String url) {
        try {
            for (MultipartFile picture : pictures) {
                String fileName = picture.getOriginalFilename().replaceAll(" ", "").trim();
                File dest = new File(Constants.PICTURE_HOME + url + fileName);
                picture.transferTo(dest);
            }
            return result;
        } catch (IOException e) {
            result.setResultCode(ResultCode.ERROR_CODE);
            result.setResultMessage(ResultCode.PICTURE_UPLOAD_ERR_MSG);
            return result;
        }
    }
}
