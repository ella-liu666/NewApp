package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.StoreList;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class StoreListTask extends DataTask<List<StoreList>> {
    private OrderRtf api;

    private Context context;

    public StoreListTask(@NonNull Context context) {
        api=new OrderRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getStoreList();
    }

    @Override
    protected List<StoreList> parseData(String s) throws Exception {
        List<StoreList> response = JSONArrayToClass(s,StoreList.class);
        return response;
    }
}
