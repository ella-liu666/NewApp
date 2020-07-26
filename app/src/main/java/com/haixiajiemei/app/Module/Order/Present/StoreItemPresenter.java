package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.StoreItemTask;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemContract;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;

import java.util.List;

public class StoreItemPresenter implements StoreItemContract.PresenterAction{

    private StoreItemContract.ViewAction viewAction;
    private Context mcontext;
    private String dbName;
    private int mcid;

    public StoreItemPresenter(StoreItemContract.ViewAction viewAction, Context mcontext, String dbName, int mcid) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.dbName = dbName;
        this.mcid = mcid;
    }

    @Override
    public void doStoreItem() {
        DataLoader.run(new StoreItemTask(mcontext,dbName,mcid) {

            @Override
            protected void onResult(List<StoreItem> storeItems) throws Exception {
                viewAction.StoreItemSuccess(storeItems);
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
