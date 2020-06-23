package com.paint.service;

/**
 * 评论相关的接口
 */
public interface commentService {

    /**
     * 发布评论
     */
    void publishComment();

    /**
     * 修改评论
     */
    void updateComment();

    /**
     * 删除评论
     */
    void deleteComment();
}
