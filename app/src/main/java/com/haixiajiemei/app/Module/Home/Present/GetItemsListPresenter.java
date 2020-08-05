package com.haixiajiemei.app.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.EC.GetItemsListTask;
import com.haixiajiemei.app.Module.Home.Contract.GetItemsListContract;
import com.haixiajiemei.app.Module.Home.Model.ItemsList;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;

public class GetItemsListPresenter implements GetItemsListContract.PresenterAction {

    private GetItemsListContract.ViewAction viewAction;
    private Context context;
    private int ecCategoryID;

    public GetItemsListPresenter(GetItemsListContract.ViewAction viewAction, Context context,
                                 int ecCategoryID) {
        this.viewAction = viewAction;
        this.context = context;
        this.ecCategoryID = ecCategoryID;
    }

    @Override
    public void doItemsList() {
        DataLoader.run(new GetItemsListTask(context,ecCategoryID) {

            @Override
            protected void onResult(List<ItemsList> itemsLists) throws Exception {
                viewAction.ItemsListSuccess(itemsLists);
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
