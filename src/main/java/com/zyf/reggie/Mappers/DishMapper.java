package com.zyf.reggie.Mappers;

import com.zyf.reggie.dto.DishDto;
import com.zyf.reggie.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishMapper {
     int count(@Param("id") Long id);
     void insertDish(@Param("dish")Dish dish);
     List<Dish> selectAll();
     Dish  selectById(@Param("id") Long id);
     void deleteDishs(@Param("id") Long id);
     void updateDish(@Param("dish") Dish dish);
     void updateStatus(@Param("status") Integer status,@Param("id") Long id);
     List<Dish> selectByCategoryId(@Param("categoryId") Long categoryId,@Param("status") Integer status);
     List<DishDto> selectByCateId(@Param("categoryId") Long categoryId);

}
