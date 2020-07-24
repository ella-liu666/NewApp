package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

public interface StoreFilterContract {

    interface ViewAction {
        void StoreFilterSuccess(IdAndTxt idAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doStoreFilter();
    }
}
