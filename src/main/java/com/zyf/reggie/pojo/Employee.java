package com.zyf.reggie.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

}
