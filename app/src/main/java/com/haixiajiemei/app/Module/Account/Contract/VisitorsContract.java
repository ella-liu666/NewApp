package com.haixiajiemei.app.Module.Account.Contract;


public interface VisitorsContract {

    interface ViewAction {

        void VisitorsSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doVisitors();

    }
}
