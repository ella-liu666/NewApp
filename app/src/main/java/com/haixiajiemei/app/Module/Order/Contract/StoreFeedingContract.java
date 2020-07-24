package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

public interface StoreFeedingContract {

    interface ViewAction {
        void StoreFeedingSuccess(IdAndTxt idAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doStoreFeeding();
    }
}
