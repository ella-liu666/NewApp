package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.StoredValueTask;
import com.haixiajiemei.app.Module.Setting.Contract.StoredValueContract;
import com.haixiajiemei.app.Module.Setting.Model.StoredValue;

public class StoredValuePresenter implements StoredValueContract.PresenterAction {

    private StoredValueContract.ViewAction viewAction;
    private Context mcontext;
    private String orderNo;
    private float value;
    private String sourceID;//1:微信，2:支付寶

    public StoredValuePresenter(StoredValueContract.ViewAction viewAction, Context mcontext, String orderNo, float value, String sourceID) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.orderNo = orderNo;
        this.value = value;
        this.sourceID = sourceID;
    }

    @Override
    public void doStoredValue() {
        DataLoader.run(new StoredValueTask(mcontext,orderNo,value,sourceID) {

            @Override
            protected void onResult(StoredValue storedValue) throws Exception {
                viewAction.StoredValueSuccess();
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
