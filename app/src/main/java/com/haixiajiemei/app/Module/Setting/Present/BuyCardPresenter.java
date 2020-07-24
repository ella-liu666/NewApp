package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.BuyCardTask;
import com.haixiajiemei.app.Module.Setting.Contract.BuyCardContract;

public class BuyCardPresenter implements BuyCardContract.PresenterAction {

    private BuyCardContract.ViewAction viewAction;
    private Context mcontext;
    private int cardID;
    private int cardCategoryID;
    private int type;
    private String cardName;
//    private float cardPrice;
    private String cardPrice;
    private float upgradeCardPrice;
    private int cardCurrentAmount;

    public BuyCardPresenter(BuyCardContract.ViewAction viewAction, Context mcontext, int cardID,
            int cardCategoryID, int type, String cardName, String cardPrice, float upgradeCardPrice,
            int cardCurrentAmount) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.cardID = cardID;
        this.cardCategoryID = cardCategoryID;
        this.type = type;
        this.cardName = cardName;
        this.cardPrice = cardPrice;
        this.upgradeCardPrice = upgradeCardPrice;
        this.cardCurrentAmount = cardCurrentAmount;
    }

    @Override
    public void doBuyCard() {
        DataLoader.run(new BuyCardTask(mcontext,cardID,cardCategoryID,type,cardName,cardPrice,
                upgradeCardPrice,cardCurrentAmount) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.BuyCardSuccess();
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
