package com.zyf.reggie.Service;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.dto.SetmealDto;
import com.zyf.reggie.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    int count(Long id);
    List<SetmealDto> getAllByCategoryId(Long categoryId,Integer status);
    PageInfo<Setmeal> getPage(int page, int pageSize);
}
