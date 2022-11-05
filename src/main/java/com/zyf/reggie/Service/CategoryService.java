package com.zyf.reggie.Service;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.pojo.Category;

import java.util.List;

public interface CategoryService {
    void insertById(Category category);
    PageInfo<Category> selectAll(int page,int pageSize);
    void delete(Long id);
    void update(Category category);
    List<Category> list(Integer type);
    String getNameById(Long id);
    List<Category> selectAllTo();
}
