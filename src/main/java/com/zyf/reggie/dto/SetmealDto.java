package com.zyf.reggie.dto;

import com.zyf.reggie.pojo.Setmeal;
import com.zyf.reggie.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
