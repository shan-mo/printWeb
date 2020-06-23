package com.example.paint.service;


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
     */
    void publishInvitation();

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
