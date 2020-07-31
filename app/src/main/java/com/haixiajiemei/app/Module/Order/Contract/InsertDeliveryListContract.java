package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;

public interface InsertDeliveryListContract {

    interface ViewAction {

        void InsertDeliveryListSuccess(SuccessMessage successMessage);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }

    interface PresenterAction {

        void doInsertDeliveryList();

    }
}
