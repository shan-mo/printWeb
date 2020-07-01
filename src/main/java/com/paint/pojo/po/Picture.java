package com.paint.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("p_picture")
public class Picture {
    @TableId(type = IdType.AUTO)
    private Long pId;
    private Long iId;
    private String url;
    private Integer type;

    public Picture() {
    }

    public Picture(String url, Integer type) {
        this.iId = iId;
        this.url = url;
        this.type = type;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pId=" + pId +
                ", iId=" + iId +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
