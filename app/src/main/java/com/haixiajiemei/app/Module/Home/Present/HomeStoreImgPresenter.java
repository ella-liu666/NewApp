package com.haixiajiemei.app.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Home.ImgInforTask;
import com.haixiajiemei.app.Module.Home.Contract.HomeStoreImgContract;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public class HomeStoreImgPresenter implements HomeStoreImgContract.PresenterAction {

    private HomeStoreImgContract.ViewAction viewAction;
    private Context mcontext;

    public HomeStoreImgPresenter(HomeStoreImgContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doHomeStoreImg() {
        DataLoader.run(new ImgInforTask(mcontext){

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxt) throws Exception {
                viewAction.HomeStoreImgContractSuccess(imgAndTxt);
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
