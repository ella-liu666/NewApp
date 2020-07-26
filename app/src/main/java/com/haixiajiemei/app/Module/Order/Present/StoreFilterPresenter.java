package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.StoreFilterTask;
import com.haixiajiemei.app.Module.Order.Contract.StoreFilterContract;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

import java.util.List;

public class StoreFilterPresenter implements StoreFilterContract.PresenterAction {

    private StoreFilterContract.ViewAction viewAction;
    private Context mcontext;
    private String dbName;
    private int dbid;

    public StoreFilterPresenter(StoreFilterContract.ViewAction viewAction, Context mcontext, int dbid, String dbName) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.dbid = dbid;
        this.dbName = dbName;
    }

    @Override
    public void doStoreFilter() {
        DataLoader.run(new StoreFilterTask(mcontext,dbid,dbName) {

            @Override
            protected void onResult(List<IdAndTxt> idAndTxts) throws Exception {
                viewAction.StoreFilterSuccess(idAndTxts);
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
