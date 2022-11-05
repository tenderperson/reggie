package com.zyf.reggie.Mappers;

import com.zyf.reggie.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void insertById(@Param("category") Category category);
    List<Category> selectAll();
    void delete(@Param("id") Long id);
    void updateCategory(@Param("category") Category category);
    List<Category> selectType(@Param("type") Integer type);
    String selectToNameById(@Param("id")Long id);
}
