package com.paint;

import com.paint.dao.userDao;
import com.paint.mapper.UserMapper;
import com.paint.pojo.bo.Result;
import com.paint.pojo.po.User;
import com.paint.util.exception.UserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PaintApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private userDao userDao;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void getUser() {
        User user = new User();
        user.setEmail("shan_mo_9@126.com");
        Result result = null;
        try {
            result = userDao.getUserinDB(user);
            user = (User) result.getResult();
            System.out.println(user);
            System.out.println(result.getResultCode() + "[" + result.getResultMessage() + "]");
        } catch (UserException e) {
            System.out.println("[" + e.getErrCode() + "]" + e.getMessage());
        }
    }

}
