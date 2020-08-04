package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.Recharge;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

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
        List<Recharge> response = JSONArrayToClass(s,Recharge.class);
        return response;
    }
}
