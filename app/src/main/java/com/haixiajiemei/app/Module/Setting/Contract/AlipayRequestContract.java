package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.PayRequest;

public interface AlipayRequestContract {

    interface ViewAction {
        void AlipayRequestSuccess(PayRequest payRequest);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doAlipayRequest();
    }
}
