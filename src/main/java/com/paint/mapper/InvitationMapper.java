package com.paint.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paint.pojo.PageModel;
import com.paint.pojo.po.Invitation;

import java.util.List;

public interface InvitationMapper extends BaseMapper<Invitation> {

    /**
     * 获取帖子总条数，为分页做准备
     * 需要使用创建时间作为条件，避免有新插入的数据造成页数混乱
     *
     * @return
     */
    Integer getInvitationCount(PageModel pageModel);

    /**
     * 分页功能显示帖子列表
     *
     * @return
     */
    List<Invitation> getInvitationPageList(PageModel pageModel);
}
