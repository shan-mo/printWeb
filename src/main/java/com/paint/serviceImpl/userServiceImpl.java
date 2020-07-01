package com.paint.serviceImpl;

import com.paint.constants.ResultCode;
import com.paint.dao.userDao;
import com.paint.pojo.Result;
import com.paint.pojo.po.User;
import com.paint.service.userService;
import com.paint.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override
    public Result login(User user) {
        Result result = new Result();
        try {
            result = userDao.getUserinDB(user);
            User DBuser = (User) result.getResult();
            if (DBuser.getPassWord().equals(user.getPassWord())) {
                return result;
            }
            throw new UserException(ResultCode.ERROR_CODE, ResultCode.PASSWORD_ERR_MSG);
        } catch (UserException e) {
            result.setResultCode(e.getErrCode());
            result.setResultMessage(e.getMessage());
            return result;
        }
    }

    @Override
    public Result regist(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public boolean loginout(User user) {
        return false;
    }

    @Override
    public boolean userCancle(User user) {
        return false;
    }

    @Override
    public boolean userMessageUpdate(User user) {
        return false;
    }

    @Override
    public void updateUserState() {

    }

    @Override
    public Result rigiestcheck(String type, String data) {
        if (type.equals("mininame")) {
            return userDao.selectForMiniName(data);
        }else {
            return userDao.selectForEmail(data);
        }
    }
}
