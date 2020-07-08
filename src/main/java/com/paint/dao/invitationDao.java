package com.paint.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paint.constants.Constants;
import com.paint.constants.ResultCode;
import com.paint.mapper.InvitationMapper;
import com.paint.mapper.PictureMapper;
import com.paint.pojo.PageModel;
import com.paint.pojo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.Picture;
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

    @Transactional
    public Result insertInvitation(Invitation invitation, MultipartFile[] pictures) throws InvitationException {
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


        //当前两步没有问题，将图片保存到磁盘中
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

//        QueryWrapper invitationWrapper = new QueryWrapper();
//        invitationWrapper.eq("state", 1);
//        List<Invitation> invitations = invitationMapper.selectList(invitationWrapper);

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
}
