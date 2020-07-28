package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.CartTask;
import com.haixiajiemei.app.Module.Order.Contract.Cartcontract;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public class CartPresenter implements Cartcontract.PresenterAction{

    private Cartcontract.ViewAction viewAction;
    private Context context;
    private String storeAccount;
    private float total;
    private String delType;
    private List<ShoppingCart> cart;

    public CartPresenter(Cartcontract.ViewAction viewAction, Context context, String storeAccount,
            float total, String delType, List<ShoppingCart> cart) {
        this.viewAction = viewAction;
        this.context = context;
        this.storeAccount = storeAccount;
        this.total = total;
        this.delType = delType;
        this.cart = cart;
    }

    @Override
    public void doCart() {
        DataLoader.run(new CartTask(context,storeAccount,total,delType,cart){

            @Override
            protected void onResult(String s) throws Exception {
               viewAction.CartSuccess();
            }

            @Override
            protected void onStart() {
                viewAction.showProgress();
            }

            @Override
            protected void onFinish() {
                viewAction.hideProgress();
            }

            @Override
            protected void onApiException(ApiException e) {
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
