package com.paint.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("p_user_invitation")
public class UserInvitation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long uId;
    private Long iId;

    public UserInvitation() {
    }

    public UserInvitation(Long uId, Long iId) {
        this.uId = uId;
        this.iId = iId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    @Override
    public String toString() {
        return "UserInvitation{" +
                "id=" + id +
                ", uId=" + uId +
                ", iId=" + iId +
                '}';
    }
}

