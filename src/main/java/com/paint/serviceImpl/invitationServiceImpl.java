package com.paint.serviceImpl;

import com.paint.constants.Constants;
import com.paint.dao.invitationDao;
import com.paint.pojo.Result;
import com.paint.pojo.po.Invitation;
import com.paint.service.invitationService;
import com.paint.util.exception.InvitationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class invitationServiceImpl implements invitationService {

    @Autowired
    private invitationDao invitationDao;

    @Override
    public void getInvitationList() {

    }

    @Override
    public Result publishInvitation(Invitation invitation, MultipartFile[] pictures) {
        Result result = null;

        invitation.setCreateDate(Constants.DATE_FORMAT.format(new Date()));
        invitation.setHavePicture((pictures.length == 0) ? 0 : 1);

        try {
            result = invitationDao.insertInvitation(invitation, pictures);
        } catch (InvitationException e) {
            result.setResultCode(e.getErrCode());
            result.setResultMessage(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public void updateInvitation() {

    }

    @Override
    public void deleteInvitation() {

    }

    @Override
    public void updateInvitationState() {

    }
}
