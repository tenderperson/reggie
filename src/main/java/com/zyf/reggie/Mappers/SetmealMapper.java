package com.zyf.reggie.Mappers;

import com.zyf.reggie.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetmealMapper {
    int count(@Param("id") Long id);
    List<Setmeal> selectByCategoryId(@Param("categoryId") Long categoryId,@Param("status") Integer status);
    List<Setmeal> selectAll();
}
