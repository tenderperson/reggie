package com.zyf.reggie.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Mappers.DishFlavorMapper;
import com.zyf.reggie.Mappers.DishMapper;
import com.zyf.reggie.Service.DishService;
import com.zyf.reggie.dto.DishDto;
import com.zyf.reggie.pojo.Dish;
import com.zyf.reggie.pojo.DishFlavor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Override
    public int count(Long id) {
        int count = dishMapper.count(id);
        return count;
    }

    @Override
    @Transactional
    public void insertDishAndDishFlavor(DishDto dishDto) {
        dishDto.setId(new Random().nextLong());
        Long id = dishDto.getId();
        dishDto.setSort(1);
        dishDto.setIsDeleted(0);
        dishMapper.insertDish(dishDto);
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach(flavor->{
            flavor.setId(new Random().nextLong());
            flavor.setDishId(id);
            flavor.setCreateTime(new Date());
            flavor.setUpdateTime(new Date());
            flavor.setCreateUser(new Long((long)1));
            flavor.setUpdateUser(new Long((long)1));
            flavor.setIsDeleted(0);
            dishFlavorMapper.insertDishFlavor(flavor);
        });
    }

    @Override
    public PageInfo<Dish> getAll(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Dish> dishes = dishMapper.selectAll();
        PageInfo<Dish> dishPageInfo = new PageInfo<>(dishes);

        return dishPageInfo;
    }

    @Override
    public DishDto getDishById(Long id) {
        Dish dish = dishMapper.selectById(id);
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishId(id);
        DishDto dishDto =new DishDto();
        dishDto.setFlavors(dishFlavors);
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setCategoryId(dish.getCategoryId());
        dishDto.setPrice(dish.getPrice());
        dishDto.setCode(dish.getCode());
        dishDto.setImage(dish.getImage());
        dishDto.setDescription(dish.getDescription());
        return dishDto;
    }

    @Override
    @Transactional
    public void updateDish(DishDto dishDto) {
        dishMapper.updateDish(dishDto);
        dishFlavorMapper.deleteDishFlavor(dishDto.getId());
        dishDto.getFlavors().forEach(flavor->{
            flavor.setId(new Random().nextLong());
            flavor.setDishId(dishDto.getId());
            dishFlavorMapper.insertDishFlavor(flavor);
        });

    }

    @Override
    public void updateStatus(Integer status, Long id) {
        dishMapper.updateStatus(status, id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dishMapper.deleteDishs(id);
        dishFlavorMapper.deleteDishFlavor(id);

    }

    @Override
    public List<DishDto> getDishByCategoryId(Long categoryId,Integer status) {
        List<DishDto> collect=null;
        if (status!=null){
            List<Dish> dishList = dishMapper.selectByCategoryId(categoryId,status);
             collect = dishList.stream().map((item) -> {
                DishDto dishDto = new DishDto();
                BeanUtils.copyProperties(item, dishDto);
                List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishId(item.getId());
                dishDto.setFlavors(dishFlavors);
                return dishDto;
            }).collect(Collectors.toList());

        }else {
            collect = dishMapper.selectByCateId(categoryId);

        }
        return collect;
    }
}
