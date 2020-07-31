package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.StoreItem;

import java.util.List;

public interface StoreItemContract {

    interface ViewAction {
        void StoreItemSuccess(List<StoreItem> storeItem);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doStoreItem();
    }
}
