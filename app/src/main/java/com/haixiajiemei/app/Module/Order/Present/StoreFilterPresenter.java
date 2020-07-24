package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.StoreFilterTask;
import com.haixiajiemei.app.Module.Order.Contract.StoreFilterContract;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

public class StoreFilterPresenter implements StoreFilterContract.PresenterAction {

    private StoreFilterContract.ViewAction viewAction;
    private Context mcontext;
    private String dbName;

    public StoreFilterPresenter(StoreFilterContract.ViewAction viewAction, Context mcontext, String dbName) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.dbName = dbName;
    }

    @Override
    public void doStoreFilter() {
        DataLoader.run(new StoreFilterTask(mcontext,dbName) {

            @Override
            protected void onResult(IdAndTxt idAndTxt) throws Exception {
                viewAction.StoreFilterSuccess(idAndTxt);
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
