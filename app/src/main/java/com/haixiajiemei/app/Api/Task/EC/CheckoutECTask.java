package com.haixiajiemei.app.Api.Task.EC;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.ECRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public class CheckoutECTask extends DataTask<String> {
    private ECRtf api;

    private Context context;
    private float total;
    private String delType;
    private int deliveryID;
    private List<ShoppingCart> cart;

    public CheckoutECTask(Context context, float total, String delType, int deliveryID, List<ShoppingCart> cart) {
        api=new ECRtf(context);

        this.context = context;
        this.total = total;
        this.delType = delType;
        this.deliveryID = deliveryID;
        this.cart = cart;
    }

    @Override
    protected String load() throws Exception {
        return api.getCheckoutEC(total, delType, deliveryID, cart);
    }

    @Override
    protected String parseData(String s) throws Exception {
        String point = s.replace("\"", "");
        return point;
    }
}
