package com.zyf.reggie.Service.Impl;

import com.zyf.reggie.Mappers.ShoppingCartMapper;
import com.zyf.reggie.Service.ShoppingCartService;
import com.zyf.reggie.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public void addShoppingCart(ShoppingCart shoppingCart) {

            if (shoppingCart.getDishId()!=null){
                ShoppingCart shoppingCart1 = shoppingCartMapper.selectDishIdAndUserId(shoppingCart.getUserId(), shoppingCart.getDishId());
                if (shoppingCart1!=null){
                    shoppingCartMapper.updateNumber(shoppingCart1.getId());
                }else {
                    shoppingCartMapper.insertDish(shoppingCart);
                }
            }else {
                ShoppingCart shoppingCart1 = shoppingCartMapper.selectSetmealIdAndUserId(shoppingCart.getUserId(), shoppingCart.getSetmealId());
                if (shoppingCart1!=null){
                    shoppingCartMapper.updateNumber(shoppingCart1.getId());
                }else {
                    shoppingCartMapper.insertSetmeal(shoppingCart);
                }
            }
        }


    @Override
    public List<ShoppingCart> getAll(Long userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByUserId(userId);
        return shoppingCarts;
    }

    @Override
    public void deleteDishOrSetmeal(ShoppingCart shoppingCart) {
        if (shoppingCart.getDishId()!=null){
            shoppingCartMapper.deleteDish(shoppingCart.getDishId());
        }else {
            shoppingCartMapper.deleteSetmeal(shoppingCart.getSetmealId());
        }
    }

    @Override
    public void deleteAll() {
       shoppingCartMapper.deleteAll();
    }
}
