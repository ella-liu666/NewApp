package com.haixiajiemei.member.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Home.ImgInforTask;
import com.haixiajiemei.member.Module.Home.Contract.HomeStoreImgContract;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
