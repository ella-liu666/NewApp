package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import androidx.annotation.NonNull;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.Address;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class DeliveryListTask extends DataTask<List<Address>> {
    private OrderRtf api;

    private Context context;

    public DeliveryListTask(@NonNull Context context) {
        api=new OrderRtf(context);
    }

    @Override
    protected String load() throws Exception {
        return api.GetDeliveryList();
    }

    @Override
    protected List<Address> parseData(String s) throws Exception {
        List<Address> response = JSONArrayToClass(s, Address.class);
        return response;
    }
}
