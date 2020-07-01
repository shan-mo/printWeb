package com.paint.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("p_invitation")
public class Invitation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String createDate;
    private Integer havePicture;
    private Integer star;
    private Integer state;

    public Invitation(){}

    public Invitation(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getHavePicture() {
        return havePicture;
    }

    public void setHavePicture(Integer havePicture) {
        this.havePicture = havePicture;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createDate='" + createDate + '\'' +
                ", havePicture=" + havePicture +
                ", star=" + star +
                ", state=" + state +
                '}';
    }
}
