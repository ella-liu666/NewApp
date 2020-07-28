package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.StoreFeed;

public interface StoreFeedingContract {

    interface ViewAction {
        void StoreFeedingSuccess(StoreFeed storeFeed);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doStoreFeeding();
    }
}
