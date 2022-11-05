package com.zyf.reggie.Controller;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Service.SetmealService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.dto.SetmealDto;
import com.zyf.reggie.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    //实现分页
    @GetMapping("/page")
    public R<PageInfo> page(int page,int pageSize){
        PageInfo<Setmeal> page1 = setmealService.getPage(page, pageSize);
        return R.success(page1);
    }

@GetMapping("/list")
    public R<List<SetmealDto>> list(SetmealDto setmealDto){
        //
    List<SetmealDto> allByCategoryId = setmealService.getAllByCategoryId(setmealDto.getCategoryId(), setmealDto.getStatus());
    return R.success(allByCategoryId);
    }
    public R<String> add(@RequestBody SetmealDto setmealDto){
        return null;
    }
}
