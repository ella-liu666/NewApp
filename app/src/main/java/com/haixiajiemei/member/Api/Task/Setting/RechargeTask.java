package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.Recharge;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToRecharge;

public class RechargeTask extends DataTask<List<Recharge>> {
    private SettingRtf api;

    public RechargeTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getRecharge();
    }

    @Override
    protected List<Recharge> parseData(String s) throws Exception {
        List<Recharge> response = JSONArrayToRecharge(s);
        return response;
    }
}
