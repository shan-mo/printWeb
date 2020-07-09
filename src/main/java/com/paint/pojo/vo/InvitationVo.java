package com.paint.pojo.vo;

import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.Picture;
import com.paint.pojo.po.User;

import java.util.List;

public class InvitationVo {
    private User user;
    private Invitation invitation;
    private List<Picture> pictureList;
    private Integer pageCount;

    public InvitationVo() {

    }

    public InvitationVo(Invitation invitation) {
        this.invitation = invitation;
    }

    public InvitationVo(Invitation invitation, List<Picture> pictureList) {
        this.invitation = invitation;
        this.pictureList = pictureList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {

        this.pictureList = pictureList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "InvitationVo{" +
                "user=" + user +
                ", invitation=" + invitation +
                ", pictureList=" + pictureList +
                ", pageCount=" + pageCount +
                '}';
    }
}
