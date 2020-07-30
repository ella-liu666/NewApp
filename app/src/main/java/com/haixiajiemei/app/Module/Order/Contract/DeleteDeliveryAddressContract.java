package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;

public interface DeleteDeliveryAddressContract {

    interface ViewAction {

        void DeleteDeliveryAddressSuccess(SuccessMessage successMessage);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doDeleteDeliveryAddress();

    }
}
