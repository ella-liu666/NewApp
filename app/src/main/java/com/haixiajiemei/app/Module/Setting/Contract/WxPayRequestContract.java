package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.PayRequest;

public interface WxPayRequestContract {

    interface ViewAction {
        void WxPayRequestSuccess(PayRequest payRequest);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doWxPayRequest();
    }
}
