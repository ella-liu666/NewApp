package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.MemberInfo;

public interface SettingContract {

    interface ViewAction {
        void SettingMemberInfoSuccess(MemberInfo MemberInfo);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doSettingMemberInfo();
    }
}
