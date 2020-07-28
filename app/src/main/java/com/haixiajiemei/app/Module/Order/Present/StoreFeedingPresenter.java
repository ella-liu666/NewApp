package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.StoreFeedingTask;
import com.haixiajiemei.app.Module.Order.Contract.StoreFeedingContract;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;
import com.haixiajiemei.app.Module.Order.Model.StoreFeed;

public class StoreFeedingPresenter implements StoreFeedingContract.PresenterAction{

    private StoreFeedingContract.ViewAction viewAction;
    private Context mcontext;
    private String dbName;
    private int mcid;

    public StoreFeedingPresenter(StoreFeedingContract.ViewAction viewAction, Context mcontext, String dbName, int mcid) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.dbName = dbName;
        this.mcid = mcid;
    }

    @Override
    public void doStoreFeeding() {
        DataLoader.run(new StoreFeedingTask(mcontext,dbName,mcid) {

            @Override
            protected void onResult(StoreFeed storeFeed) throws Exception {
                viewAction.StoreFeedingSuccess(storeFeed);
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
