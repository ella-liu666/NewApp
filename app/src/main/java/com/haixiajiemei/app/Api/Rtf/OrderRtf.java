package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import retrofit2.Call;
import retrofit2.http.GET;

public class OrderRtf extends BaseRtf<OrderRtf.Service> {

    public OrderRtf(Context context) {
        super(context);
    }

    @Override
    protected Class<OrderRtf.Service> getType() {
            return OrderRtf.Service.class;
    }

    public String getStoreList() throws Exception {
        return this.execute(this.api.StoreList());
    }

    public interface Service {

        @GET("Order/Order/StoreList")
        Call<String> StoreList();
    }
}
