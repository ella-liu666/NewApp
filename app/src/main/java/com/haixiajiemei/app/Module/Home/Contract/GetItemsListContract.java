package com.haixiajiemei.app.Module.Home.Contract;

import com.haixiajiemei.app.Module.Home.Model.ItemsList;

import java.util.List;

public interface GetItemsListContract {

    interface ViewAction {

        void ItemsListSuccess(List<ItemsList> itemsLists);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }

    interface PresenterAction {

        void doItemsList();

    }
}
