package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haixiajiemei.app.Api.Basic.Params;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class HomeRtf extends BaseRtf<HomeRtf.Service> {

    public HomeRtf(@Nullable Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return HomeRtf.Service.class;
    }

    public String getBrandImgInfor() throws Exception {
        return this.execute(this.api.BrandImgInfor());
    }

    public String getAdImgInfor() throws Exception {
        return this.execute(this.api.AdImgInfor());
    }

    public String getBrandIntroductionImg(@NonNull int id) throws Exception {
        Params params = new Params();
        params.putRequired("InfoID", id);

        return this.execute(this.api.BrandIntroductionImg(params.getMap()));
    }

    public interface Service {
        @GET("MainSite/GetAdImg")
        Call<String> AdImgInfor();

        @GET("MainSite/GetStoreImg")
        Call<String> BrandImgInfor();

        @POST("MainSite/GetStoreDetailImg")
        Call<String> BrandIntroductionImg(@Body Map<String, Object> params);
    }
}
