package com.zyf.reggie.Controller;

import com.zyf.reggie.Service.ShoppingCartService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/shoppingCart")
public class ShopCarController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    //展示购物车
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(HttpSession session){
        Long userId =(Long) session.getAttribute("user");
        List<ShoppingCart> all = shoppingCartService.getAll(userId);
        return R.success(all);
    }
    //添加购物车
    @PostMapping("/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart,HttpSession session){
        shoppingCart.setId(new Random().nextLong());
        Long userId =(Long) session.getAttribute("user");
        shoppingCart.setUserId(userId);
        shoppingCart.setCreateTime(new Date());
        shoppingCartService.addShoppingCart(shoppingCart);
        return  R.success("添加购物车成功");
    }
    //删除单个菜品
    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.deleteDishOrSetmeal(shoppingCart);
        return R.success("删除成功");
    }
    @DeleteMapping("/clean")
    public R<String> deleteAll(){
        shoppingCartService.deleteAll();
        return R.success("清理购物车成功");
    }
}
