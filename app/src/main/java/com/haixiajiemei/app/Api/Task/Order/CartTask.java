package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public class CartTask  extends DataTask<String> {
    private OrderRtf api;

    private Context context;
    private String storeAccount;
    private float total;
    private String delType;
    private List<ShoppingCart> cart;

    public CartTask(Context context, String storeAccount, float total, String delType, List<ShoppingCart> cart) {
        api = new OrderRtf(context);

        this.context = context;
        this.storeAccount = storeAccount;
        this.total = total;
        this.delType = delType;
        this.cart = cart;
    }

    @Override
    protected String load() throws Exception {
        return api.getCart(storeAccount,total,delType,cart);
    }

    @Override
    protected String parseData(String s) throws Exception {
        String point=s.replace("\"", "");
        return point;
    }

}
