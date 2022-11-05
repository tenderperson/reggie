package com.zyf.reggie.Mappers;

import com.zyf.reggie.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> selectByUserId(@Param("userid")Long userId);
    void updateNumber(@Param("id")Long id);
    void insertDish(@Param("shopcart") ShoppingCart shoppingCart);
    void insertSetmeal(@Param("shopcart") ShoppingCart shoppingCart);
    void deleteDish(@Param("dishid") Long dishId);
    void deleteSetmeal(@Param("setmealid") Long setmeal);
    void deleteAll();
    ShoppingCart selectDishIdAndUserId(@Param("userid") Long userid,@Param("dishid") Long dishId);
    ShoppingCart selectSetmealIdAndUserId(@Param("userid") Long userid,@Param("setmealid") Long setmealId);

}
