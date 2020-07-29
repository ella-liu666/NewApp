package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public interface ShoppingCartItemCallback {
//    void onShoppingCartItemClicked();

    void onRecyclerViewUpData(List<ShoppingCart> cart);
}
