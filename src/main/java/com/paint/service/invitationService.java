package com.paint.service;


import com.paint.pojo.bo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子相关的接口
 */
public interface invitationService {

    /**
     * 浏览帖子列表
     */
    Result getInvitationList(Result result, String page, String firstcreateDate);

    /**
     * 通过帖子id获取帖子的具体星系
     *
     * @param result
     * @param invitationId
     * @return
     */
    Result getInvitation(Result result, Integer invitationId);

    /**
     * 发布帖子
     * 通过事务，将帖子内容和图片存入数据库
     * 如果插入正确，将图片保存到磁盘中
     * 如果插入数据库有误，回滚操作
     */
    Result publishInvitation(User user, Invitation invitation, MultipartFile[] pictures);

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
