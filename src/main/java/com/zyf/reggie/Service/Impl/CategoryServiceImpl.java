package com.zyf.reggie.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Mappers.CategoryMapper;
import com.zyf.reggie.Mappers.DishMapper;
import com.zyf.reggie.Mappers.SetmealMapper;
import com.zyf.reggie.Service.CategoryService;
import com.zyf.reggie.common.CustomException;
import com.zyf.reggie.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Override
    public void insertById(Category category) {
        categoryMapper.insertById(category);
    }

    @Override
    public PageInfo<Category> selectAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Category> categories = categoryMapper.selectAll();
        PageInfo<Category> pageInfo =new PageInfo<>(categories);
        return pageInfo;
    }

    @Override
    public void delete(Long id) {
        int count1 = dishMapper.count(id);
        int count2 = setmealMapper.count(id);
        if (count1!=0){
           throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        if (count2!=0){
          throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        categoryMapper.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public List<Category> list(Integer type) {
        List<Category> categories = categoryMapper.selectType(type);
        return categories;
    }

    @Override
    public String getNameById(Long id) {
        String name = categoryMapper.selectToNameById(id);
        return name;
    }

    @Override
    public List<Category> selectAllTo() {
        List<Category> categories = categoryMapper.selectAll();
        return categories;
    }
}
