package com.zyf.reggie.Service;

import com.zyf.reggie.pojo.User;

public interface UserService {
    User userByPhone(String phone);
    void insertOne(User user);
}
