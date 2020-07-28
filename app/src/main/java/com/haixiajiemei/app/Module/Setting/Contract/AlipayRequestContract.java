package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.AlipayRequest;

public interface AlipayRequestContract {

    interface ViewAction {
        void AlipayRequestSuccess(AlipayRequest alipayRequest);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doAlipayRequest();
    }
}
