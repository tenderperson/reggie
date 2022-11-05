package com.zyf.reggie.Controller;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Service.CategoryService;
import com.zyf.reggie.Service.DishService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.dto.DishDto;
import com.zyf.reggie.pojo.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public R<String> addDish(HttpServletRequest request, @RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishDto.setCreateTime(new Date());
        dishDto.setUpdateTime(new Date());
        Long empId =(Long) request.getSession().getAttribute("employee");
        dishDto.setCreateUser(empId);
        dishDto.setUpdateUser(empId);
        dishService.insertDishAndDishFlavor(dishDto);
        return R.success("新增菜品成功");
    }
    //实现菜品的分页
    @GetMapping("/page")
    public R<PageInfo<DishDto>> page(int page,int pageSize){
        PageInfo<Dish> all = dishService.getAll(page, pageSize);
        PageInfo<DishDto> dishDtoPageInfo =new PageInfo<>();
        BeanUtils.copyProperties(all,dishDtoPageInfo,"list");
        List<Dish> dishList = all.getList();
        List<DishDto> dishDtoList = null;
        List<DishDto> collect = dishList.stream().map((dish) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish, dishDto);
            Long categoryId = dish.getCategoryId();
            String nameById = categoryService.getNameById(categoryId);
            dishDto.setCategoryName(nameById);
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPageInfo.setList(collect);
        return R.success(dishDtoPageInfo);

    }
    //回显菜品
    @GetMapping("/{id}")
    public R<DishDto> showDish(@PathVariable("id") Long id){
        //回显，根据dishid查
        DishDto dishById = dishService.getDishById(id);
        return R.success(dishById);
    }

    //修改菜品
    @PutMapping
    public R<String> updateDish(@RequestBody DishDto dishDto){
        dishService.updateDish(dishDto);
        return R.success("修改成功");
    }
    //删除一个或多个菜品
    @DeleteMapping
    public R<String> deleteDish(Long ids[]){
        for (int i = 0;i<ids.length;i++){
            Long id = ids[i];
            dishService.deleteById(id);
        }


        return R.success("删除成功");
    }
    //修改一个或多个菜品的status
    @PostMapping("/status/{status}")
    public R<String> stopAndStartSales(@PathVariable("status") Integer status,Long ids[]){
        for (int i=0;i<ids.length;i++){
            Long id = ids[i];
            dishService.updateStatus(status,id);
        }
        return R.success("成功");
    }
    //回显菜品
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        List<DishDto> dishByCategoryId = dishService.getDishByCategoryId(dish.getCategoryId(),dish.getStatus());
        return R.success(dishByCategoryId);
    }
}
