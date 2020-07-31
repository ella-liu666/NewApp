package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.StoreList;

import java.util.List;

public interface StoreListContract {

    interface ViewAction {
        void StoreListSuccess(List<StoreList> storeListList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doStoreList();
    }
}
