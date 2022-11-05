package com.zyf.reggie.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Mappers.DishMapper;
import com.zyf.reggie.Mappers.SetmealMapper;
import com.zyf.reggie.Service.SetmealService;
import com.zyf.reggie.dto.SetmealDto;
import com.zyf.reggie.pojo.Setmeal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Override
    public int count(Long id) {
        int count = setmealMapper.count(id);
        return count;
    }

    @Override
    public List<SetmealDto> getAllByCategoryId(Long categoryId, Integer status) {
        List<Setmeal> setmeals = setmealMapper.selectByCategoryId(categoryId, status);
        List<SetmealDto> collect = setmeals.stream().map((itea) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(itea, setmealDto);
            return setmealDto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageInfo<Setmeal> getPage(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Setmeal> setmeals = setmealMapper.selectAll();
        PageInfo<Setmeal> objectPageInfo = new PageInfo<>(setmeals);
        return objectPageInfo;
    }
}
