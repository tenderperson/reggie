package com.zyf.reggie.Service;

import com.zyf.reggie.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCart shoppingCart);
    List<ShoppingCart> getAll(Long id);
    void deleteDishOrSetmeal(ShoppingCart shoppingCart);
    void deleteAll();
}
