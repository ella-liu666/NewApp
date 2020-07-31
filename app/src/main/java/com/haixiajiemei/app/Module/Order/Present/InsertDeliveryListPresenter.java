package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.InsertDeliveryListTask;
import com.haixiajiemei.app.Module.Order.Contract.InsertDeliveryListContract;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;

public class InsertDeliveryListPresenter implements InsertDeliveryListContract.PresenterAction {

    private InsertDeliveryListContract.ViewAction viewAction;
    private Context mcontext;
    private String name;
    private String gender;
    private String telephone;
    private String address;
    private String memo;

    public InsertDeliveryListPresenter(InsertDeliveryListContract.ViewAction viewAction, Context mcontext, String name, String gender, String telephone, String address, String memo) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.name = name;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.memo = memo;
    }

    @Override
    public void doInsertDeliveryList() {
        DataLoader.run(new InsertDeliveryListTask(mcontext,name,gender,telephone,address,memo) {

            @Override
            protected void onResult(SuccessMessage successMessage) throws Exception {
                viewAction.InsertDeliveryListSuccess(successMessage);
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
