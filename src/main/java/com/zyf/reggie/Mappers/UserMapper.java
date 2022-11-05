package com.zyf.reggie.Mappers;

import com.zyf.reggie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectByPhone(@Param("phone") String phone);
    void insertOne(@Param("user") User user);
}
