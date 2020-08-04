package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;
import android.util.Log;
import android.widget.Space;

import com.haixiajiemei.app.Api.Basic.Params;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    public String getStoreFilter(@NonNull int dbid,@NonNull String dbName) throws Exception {
        Params params = new Params();
        params.putRequired("dbid", dbid);
        params.putRequired("dbName", dbName);

        return this.execute(this.api.StoreFilter(params.getMap()));
    }

    public String getStoreItem(@NonNull String dbName,@NonNull int mcid) throws Exception {
        Params params = new Params();
        params.putRequired("dbName", dbName);
        params.putRequired("mcid", mcid);

        return this.execute(this.api.StoreItem(params.getMap()));
    }

    public String getStoreFeeding(@NonNull String dbName,@NonNull int mcid) throws Exception {
        Params params = new Params();
        params.putRequired("dbName", dbName);
        params.putRequired("mcid", mcid);

        return this.execute(this.api.StoreFeeding(params.getMap()));
    }

    public String getCart(@NonNull String storeAccount,@NonNull float total,@NonNull String delType,@NonNull List<ShoppingCart> cart,@NonNull int deliveryID) throws Exception {
        Params params = new Params();
        params.putRequired("storeAccount", storeAccount);
        params.putRequired("total", total);
        params.putRequired("delType", delType);
        params.putRequired("cart", cart);
        params.putRequired("deliveryID", deliveryID);
        return this.execute(this.api.Cart(params.getMap()));
    }

    public String GetDeliveryList() throws Exception {
        return this.execute(this.api.GetDeliveryList());
    }

    public String getInsertDeliveryList(@NonNull String name, @NonNull String gender, @NonNull String telephone, @NonNull String address, @NonNull String memo) throws Exception {
        Params params = new Params();
        params.putRequired("name", name);
        params.putRequired("gender", gender);
        params.putRequired("telephone", telephone);
        params.putRequired("address", address);
        params.putRequired("memo", memo);

        return this.execute(this.api.InsertDeliveryList(params.getMap()));
    }

    public String getDeleteDeliveryAddress(@NonNull int deliveryID) throws Exception {
        Params params = new Params();
        params.putRequired("deliveryID", deliveryID);

        return this.execute(this.api.DeleteDeliveryAddress(params.getMap()));
    }


    public interface Service {
        @GET("Order/GetStoreList")
        Call<String> StoreList();

        @POST("Order/GetCategoryList")
        Call<String> StoreFilter(@Body Map<String, Object> params);

        @POST("Order/GetMealsList")
        Call<String> StoreItem(@Body Map<String, Object> params);

        @POST("Order/GetMealsDetail")
        Call<String> StoreFeeding(@Body Map<String, Object> params);

        @POST("Order/CheckoutOrder")
        Call<String> Cart(@Body Map<String, Object> params);

        @GET("Member/GetDeliveryList")
        Call<String> GetDeliveryList();

        @POST("Member/InsertDeliveryList")
        Call<String> InsertDeliveryList(@Body Map<String, Object> params);

        @POST("Member/DeleteDeliveryAddress")
        Call<String> DeleteDeliveryAddress(@Body Map<String, Object> params);
    }
}
