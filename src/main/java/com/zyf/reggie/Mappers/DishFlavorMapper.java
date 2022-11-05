package com.zyf.reggie.Mappers;

import com.zyf.reggie.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    void insertDishFlavor(@Param("dishflavor")DishFlavor dishFlavor);
    List<DishFlavor> selectByDishId(@Param("id") Long id);
    void deleteDishFlavor(@Param("dishid")Long id);

}
