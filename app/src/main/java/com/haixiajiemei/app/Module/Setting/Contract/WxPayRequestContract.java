package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.WxPayRequest;

public interface WxPayRequestContract {

    interface ViewAction {
        void WxPayRequestSuccess(WxPayRequest wxPayRequest);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doWxPayRequest();
    }
}
