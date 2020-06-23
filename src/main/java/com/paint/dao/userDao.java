package com.paint.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paint.constants.ResultCode;
import com.paint.mapper.UserMapper;
import com.paint.pojo.Result;
import com.paint.pojo.po.User;
import com.paint.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class userDao {
    @Autowired
    private UserMapper userMapper;

    public Result getUserinDB(User user) throws UserException {
        Result result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", user.getEmail());
        Optional.ofNullable(user = userMapper.selectOne(wrapper)).orElseThrow(() -> new UserException(ResultCode.USER_NOT_FOUND, ResultCode.USER_NOT_FOUND_MSG));
        result.setResult(user);
        result.setResultCode(ResultCode.USER_FOUND);
        result.setResultMessage(ResultCode.USER_FOUND_MSG);
        return result;
    }

}
