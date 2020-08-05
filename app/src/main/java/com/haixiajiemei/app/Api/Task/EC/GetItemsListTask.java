package com.haixiajiemei.app.Api.Task.EC;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.ECRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ItemsList;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;


public class GetItemsListTask extends DataTask<List<ItemsList>> {
    private ECRtf api;

    private Context context;
    private int ecCategoryID;

    public GetItemsListTask(Context context,int ecCategoryID) {
        api=new ECRtf(context);

        this.context = context;
        this.ecCategoryID = ecCategoryID;
    }

    @Override
    protected String load() throws Exception {
        return api.getItemsList(ecCategoryID);
    }

    @Override
    protected List<ItemsList> parseData(String s) throws Exception {
        List<ItemsList> response = JSONArrayToClass(s,ItemsList.class);
        return response;
    }
}
