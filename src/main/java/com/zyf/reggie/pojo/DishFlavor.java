package com.zyf.reggie.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
菜品口味
 */
@Data
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //菜品id
    private Long dishId;


    //口味名称
    private String name;


    //口味数据list
    private String value;



    private Date createTime;



    private Date updateTime;



    private Long createUser;



    private Long updateUser;


    //是否删除
    private Integer isDeleted;

}
