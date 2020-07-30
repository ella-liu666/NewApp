package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.WxPayRequest;

public interface WxPayRequestContract {

    interface ViewAction {
        void WxPayRequestSuccess(WxPayRequest wxPayRequest);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doWxPayRequest();
    }
}
