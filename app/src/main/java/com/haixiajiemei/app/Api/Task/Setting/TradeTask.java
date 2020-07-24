package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.Expenses;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

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
//        List<Expenses> response = JSONArrayToExpenses(s);
        List<Expenses> response = JSONArrayToClass(s,Expenses.class);
        return response;
    }
}
