package com.haixiajiemei.app.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.EC.CheckoutECTask;
import com.haixiajiemei.app.Module.Home.Contract.CheckoutECContract;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public class CheckoutECPresenter implements CheckoutECContract.PresenterAction{
    private CheckoutECContract.ViewAction viewAction;
    private Context context;
    private float total;
    private String delType;
    private int deliveryID;
    private List<ShoppingCart> cart;

    public CheckoutECPresenter(CheckoutECContract.ViewAction viewAction, Context context,
                                 float total, String delType, int deliveryID, List<ShoppingCart> cart) {
        this.viewAction = viewAction;
        this.context = context;
        this.total = total;
        this.delType = delType;
        this.deliveryID = deliveryID;
        this.cart = cart;
    }

    @Override
    public void doCheckoutEC() {
        DataLoader.run(new CheckoutECTask(context, total, delType, deliveryID, cart) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.CheckoutECSuccess(s);
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
                viewAction.ApierrorOccurred(e.getErrorBody().getAccess_token());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
