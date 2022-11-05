package com.zyf.reggie.Service.Impl;

import com.zyf.reggie.Mappers.UserMapper;
import com.zyf.reggie.Service.UserService;
import com.zyf.reggie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userByPhone(String phone) {
        User user = userMapper.selectByPhone(phone);
        return user;
    }

    @Override
    public void insertOne(User user) {
        userMapper.insertOne(user);

    }
}
