package com.haixiajiemei.app.Module.Setting.Contract;


public interface StoredValueContract {

    interface ViewAction {
        void StoredValueSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doStoredValue();
    }
}
