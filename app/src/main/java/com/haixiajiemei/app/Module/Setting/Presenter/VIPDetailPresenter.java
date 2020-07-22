package com.haixiajiemei.app.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.VIPDetailTask;
import com.haixiajiemei.app.Module.Setting.Contract.VIPDetailContract;
import com.haixiajiemei.app.Module.Setting.Model.CardDetail;

public class VIPDetailPresenter implements VIPDetailContract.PresenterAction {

    private VIPDetailContract.ViewAction viewAction;
    private Context mcontext;
    private int cardID;

    public VIPDetailPresenter(VIPDetailContract.ViewAction viewAction, Context mcontext, int cardID) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.cardID = cardID;
    }

    @Override
    public void doVIPDetail() {
        DataLoader.run(new VIPDetailTask(mcontext,cardID) {

            @Override
            protected void onResult(CardDetail cardDetail) throws Exception {
                viewAction.VIPDetailSuccess(cardDetail);
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
