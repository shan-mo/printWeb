package com.paint.dao;

import com.paint.constants.Constants;
import com.paint.constants.ResultCode;
import com.paint.mapper.InvitationMapper;
import com.paint.mapper.PictureMapper;
import com.paint.pojo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.pojo.po.Picture;
import com.paint.util.exception.InvitationException;
import com.paint.util.pictureUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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
        if (invitationMapper.insert(invitation) == 1) {

            List<Picture> pictureList = pictureUpload.getPictureList(pictures);
            int pictureSize = pictureList.size();

            //插入图片路径到数据库
            for (Picture picture : pictureList) {
                picture.setiId(invitation.getId());
                picture.setUrl(Constants.PICTURE_HOME + invitation.getId() + datestemp + picture.getUrl());
                pictureSize -= pictureMapper.insert(picture);
            }

            //当前两步没有问题，将图片保存到磁盘中
            if (pictureSize == 0) {
                pictureUpload.upload(result, pictures, invitation.getId() + datestemp);

                if (result.getResultCode().equals(ResultCode.SUCCESS_CODE)) {
                    result.setResultCode(ResultCode.SUCCESS_CODE);
                    result.setResultMessage(ResultCode.INVITATION_INSERT_SUCCESS);
                    return result;
                    
                } else {
                    throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);
                }
            } else {
                throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);
            }
        } else {
            throw new InvitationException(ResultCode.ERROR_CODE, ResultCode.INVITATION_INSERT_ERR_MSG);
        }
    }
}
