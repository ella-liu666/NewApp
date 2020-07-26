package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;
import com.haixiajiemei.app.Parser.ClassParser;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class StoreItemTask extends DataTask<List<StoreItem>> {
    private OrderRtf api;

    private Context context;
    private String dbName;
    private int mcid;

    public StoreItemTask(Context context, String dbName, int mcid) {
        api = new OrderRtf(context);

        this.context = context;
        this.dbName = dbName;
        this.mcid = mcid;
    }

    @Override
    protected String load() throws Exception {
        return api.getStoreItem(dbName,mcid);
    }

    @Override
    protected List<StoreItem> parseData(String s) throws Exception {
        List<StoreItem> response = JSONArrayToClass(s,StoreItem.class);
        return response;
    }
}
