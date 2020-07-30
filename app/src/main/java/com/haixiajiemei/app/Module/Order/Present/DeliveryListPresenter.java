package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.DeliveryListTask;
import com.haixiajiemei.app.Module.Order.Contract.DeliveryListContract;
import com.haixiajiemei.app.Module.Order.Model.Address;

import java.util.List;

public class DeliveryListPresenter implements DeliveryListContract.PresenterAction {

    private DeliveryListContract.ViewAction viewAction;
    private Context mcontext;

    public DeliveryListPresenter(DeliveryListContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doDeliveryList() {
        DataLoader.run(new DeliveryListTask(mcontext){

            @Override
            protected void onResult(List<Address> addresses) throws Exception {
                viewAction.DeliveryListSuccess(addresses);
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
