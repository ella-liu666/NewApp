package com.haixiajiemei.app.Module.Order.Contract;

import com.haixiajiemei.app.Module.Order.Model.Address;

import java.util.List;

public interface DeliveryListContract {

    interface ViewAction {
        void DeliveryListSuccess(List<Address> DeliveryList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doDeliveryList();
    }
}
