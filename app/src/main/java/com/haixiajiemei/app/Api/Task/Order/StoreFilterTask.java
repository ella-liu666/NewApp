package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class StoreFilterTask extends DataTask<List<IdAndTxt>> {
    private OrderRtf api;

    private Context context;
    private String dbName;
    private int dbid;

    public StoreFilterTask(Context context, int dbid, String dbName) {
        api = new OrderRtf(context);

        this.context = context;
        this.dbid = dbid;
        this.dbName = dbName;
    }

    @Override
    protected String load() throws Exception {
        return api.getStoreFilter(dbid,dbName);
    }

    @Override
    protected List<IdAndTxt> parseData(String s) throws Exception {
        List<IdAndTxt> response = JSONArrayToClass(s,IdAndTxt.class);
        return response;
    }
}
