package com.paint.service;


import com.paint.pojo.Result;
import com.paint.pojo.po.Invitation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 帖子相关的接口
 */
public interface invitationService {

    /**
     * 浏览帖子列表
     */
    void getInvitationList();

    /**
     * 发布帖子
     * 通过事务，将帖子内容和图片存入数据库
     * 如果插入正确，将图片保存到磁盘中
     * 如果插入数据库有误，回滚操作
     */
    Result publishInvitation(Invitation invitation, MultipartFile[] pictures);

    /**
     * 修改帖子内容
     */
    void updateInvitation();

    /**
     * 删除帖子
     */
    void deleteInvitation();

    /**
     * 修改帖子状态
     */
    void updateInvitationState();


}
