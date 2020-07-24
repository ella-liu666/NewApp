package com.haixiajiemei.app.Module.Order.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Order.StoreListTask;
import com.haixiajiemei.app.Module.Order.Contract.StoreListContract;
import com.haixiajiemei.app.Module.Order.Model.StoreList;

import java.util.List;

public class StoreListPresenter implements StoreListContract.PresenterAction{

    private StoreListContract.ViewAction viewAction;
    private Context mcontext;

    public StoreListPresenter(StoreListContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doStoreList() {
        DataLoader.run(new StoreListTask(mcontext){

            @Override
            protected void onResult(List<StoreList> storeListList) throws Exception {
               viewAction.StoreListSuccess(storeListList);
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
