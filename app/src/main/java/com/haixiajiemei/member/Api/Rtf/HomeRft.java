package com.haixiajiemei.member.Api.Rtf;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class HomeRft extends BaseRtf<HomeRft.Service> {

    public HomeRft(@Nullable Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return HomeRft.Service.class;
    }

    public String getBrandImgInfor() throws Exception {
        return this.execute(this.api.BrandImgInfor());
    }

    public String getAdImgInfor() throws Exception {
        return this.execute(this.api.AdImgInfor());
    }

    public String getBrandIntroductionImg(@NonNull int id) throws Exception {
        return this.execute(this.api.BrandIntroductionImg(id));
    }

    public interface Service {
        @GET("MainSite/AdImg")
        Call<String> AdImgInfor();

        @GET("MainSite/StoreImg")
        Call<String> BrandImgInfor();

        @GET("MainSite/BlandImg")
        Call<String> BrandIntroductionImg(@Query("InfoID") int id );
    }
}
