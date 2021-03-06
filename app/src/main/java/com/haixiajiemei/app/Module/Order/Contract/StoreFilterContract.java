package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

import java.util.List;

public interface StoreFilterContract {

    interface ViewAction {
        void StoreFilterSuccess(List<IdAndTxt> idAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doStoreFilter();
    }
}
