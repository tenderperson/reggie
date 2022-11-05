package com.zyf.reggie.Service;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.dto.DishDto;
import com.zyf.reggie.pojo.Dish;

import java.util.List;

public interface DishService {
    int count(Long id);
    void insertDishAndDishFlavor(DishDto dishDto);
    PageInfo<Dish> getAll(int page,int pageSize);
    DishDto getDishById(Long id);
    void updateDish(DishDto dishDto);
    void updateStatus(Integer status,Long id);
    void deleteById(Long id);
    List<DishDto> getDishByCategoryId(Long categoryId,Integer status);

}
