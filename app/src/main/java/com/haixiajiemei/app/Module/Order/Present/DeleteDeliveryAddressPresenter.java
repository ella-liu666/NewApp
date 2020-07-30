package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.DeleteDeliveryAddressTask;
import com.haixiajiemei.app.Module.Order.Contract.DeleteDeliveryAddressContract;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;

public class DeleteDeliveryAddressPresenter implements DeleteDeliveryAddressContract.PresenterAction{

    private DeleteDeliveryAddressContract.ViewAction viewAction;
    private Context mcontext;
    private int deliveryID;

    public DeleteDeliveryAddressPresenter(DeleteDeliveryAddressContract.ViewAction viewAction, Context mcontext, int deliveryID) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.deliveryID = deliveryID;
    }

    @Override
    public void doDeleteDeliveryAddress() {
        DataLoader.run(new DeleteDeliveryAddressTask(mcontext,deliveryID) {

            @Override
            protected void onResult(SuccessMessage successMessage) throws Exception {
                viewAction.DeleteDeliveryAddressSuccess(successMessage);
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
