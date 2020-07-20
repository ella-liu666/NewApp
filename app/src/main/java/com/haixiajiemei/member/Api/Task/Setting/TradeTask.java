package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.Expenses;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToExpenses;

public class TradeTask  extends DataTask<List<Expenses>> {
    private SettingRtf api;

    public TradeTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getTrade();
    }

    @Override
    protected List<Expenses> parseData(String s) throws Exception {
        List<Expenses> response = JSONArrayToExpenses(s);
        return response;
    }
}
