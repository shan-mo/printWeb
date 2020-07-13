package com.paint.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paint.constants.Constants;
import com.paint.constants.ResultCode;
import com.paint.mapper.InvitationMapper;
import com.paint.mapper.PictureMapper;
import com.paint.mapper.UserInvitationMapper;
import com.paint.mapper.UserMapper;
import com.paint.pojo.bo.PageModel;
import com.paint.pojo.bo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.Picture;
import com.paint.pojo.po.User;
import com.paint.pojo.po.UserInvitation;
import com.paint.pojo.vo.InvitationVo;
import com.paint.util.exception.InvitationException;
import com.paint.util.pictureUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Repository
public class invitationDao {
    @Autowired
    private InvitationMapper invitationMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private UserInvitationMapper userInvitationMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Result insertInvitation(User user, Invitation invitation, MultipartFile[] pictures) throws InvitationException {
        Result result = new Result();
        String datestemp = Constants.DATE_FORMAT_SIMPLE.format(new Date());
        //插入帖子文字部分到数据库
        int i = invitationMapper.insert(invitation);
        if (i != 1)
            throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);


        List<Picture> pictureList = pictureUpload.getPictureList(pictures);
        int pictureSize = pictureList.size();

        //插入图片路径到数据库
        for (Picture picture : pictureList) {
            picture.setiId(invitation.getId());
            picture.setUrl(Constants.PICTURE_URL + invitation.getId() + datestemp + picture.getUrl());
            pictureSize -= pictureMapper.insert(picture);
        }

        if (pictureSize != 0)
            throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);

        //将用户和帖子连接起来
        userInvitationMapper.insert(new UserInvitation(user.getId(), invitation.getId()));


        //当前三步没有问题，将图片保存到磁盘中
        pictureUpload.upload(result, pictures, invitation.getId() + datestemp);


        if (!result.getResultCode().equals(ResultCode.SUCCESS_CODE))
            throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);

        return result;
    }

    public Result selectInvitationList(Result result, PageModel pageModel) {
        List<InvitationVo> list = new ArrayList<>();

        List<Invitation> invitations = invitationMapper.getInvitationPageList(pageModel);
        Integer pageCount = 1;
        Integer invitationCount = invitationMapper.getInvitationCount(pageModel);
        if (invitationCount > pageModel.getPageSize()) {
            switch (invitationCount % pageModel.getPageSize()) {
                case 0:
                    pageCount = invitationCount / pageModel.getPageSize();
                    break;
                default:
                    pageCount = invitationCount / pageModel.getPageSize() + 1;
            }

        }

        invitations.forEach(invitation -> {
            InvitationVo invitationVo = new InvitationVo(invitation);
            if (invitation.getHavePicture() == 1) {
                QueryWrapper pictureWapper = new QueryWrapper();
                pictureWapper.eq("i_id", invitation.getId());
                invitationVo.setPictureList(pictureMapper.selectList(pictureWapper));
            }
            list.add(invitationVo);
        });

        list.get(0).setPageCount(pageCount);
        result.setResult(list);
        result.setResultCode(ResultCode.SUCCESS_CODE);
        result.setResultMessage(ResultCode.INVITATION_SELECT_SUCCESS);
        return result;
    }

    public Result selectInvitation(Result result, Integer invitationId) {
        //使用optional进行判空
        Optional<Invitation> invitation = Optional.ofNullable(invitationMapper.selectById(invitationId));
        if (invitation.isPresent()) {
            InvitationVo invitationVo = new InvitationVo(invitation.get());

            //如果帖子的图片字段是1，代表有图片，需要再查一次图片列表
            if (invitationVo.getInvitation().getHavePicture() == 1) {
                QueryWrapper wrapper = new QueryWrapper();
                wrapper.eq("i_id", invitation.get().getId());
                List pictureList = pictureMapper.selectList(wrapper);
                invitationVo.setPictureList(pictureList);
            }

            //获取用户-帖子关联表信息
            QueryWrapper userinviWrapper = new QueryWrapper();
            userinviWrapper.eq("i_id", invitationId);
            UserInvitation userInvitation = userInvitationMapper.selectOne(userinviWrapper);
            //获取用户信息
            QueryWrapper userWrapper = new QueryWrapper();
            userWrapper.eq("id", userInvitation.getuId());
            invitationVo.setUser(userMapper.selectOne(userWrapper));

            result.setResult(invitationVo);
            result.setResultCode(ResultCode.SUCCESS_CODE);
            result.setResultMessage(ResultCode.INVITATION_SELECTONE_SUCCESS);
            return result;
        }

        result.setResultCode(ResultCode.ERROR_CODE);
        result.setResultMessage(ResultCode.INVITATION_SELECTONE_ERROR);
        return result;
    }
}
