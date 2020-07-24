package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.StoreItem;

public interface StoreItemContract {

    interface ViewAction {
        void StoreItemSuccess(StoreItem storeItem);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doStoreItem();
    }
}
