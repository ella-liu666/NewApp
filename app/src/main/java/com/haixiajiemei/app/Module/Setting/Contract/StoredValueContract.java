package com.haixiajiemei.app.Module.Setting.Contract;


public interface StoredValueContract {

    interface ViewAction {
        void StoredValueSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doStoredValue();
    }
}
