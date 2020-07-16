package com.haixiajiemei.member.Module.Home.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haixiajiemei.member.Helper.GlideApp;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;

public class AdvertisingAdapter extends StaticPagerAdapter {

    private ArrayList<ImgAndTxt> imgs;
    private Context context;

    public AdvertisingAdapter(Context context,ArrayList<ImgAndTxt> imgs) {
        this.imgs = imgs;
        this.context = context;
    }


    @Override
    public View getView(ViewGroup container, int position) {
        LinearLayout view=new LinearLayout(context);
        ImageView img = new ImageView(container.getContext());
//        Drawable drawable = LoadImageFromWebOperations(imgs.get(position).getImg().toString());
//        view.setImageDrawable(drawable);
//        view.setScaleType(ImageView.ScaleType.FIT_CENTER);

        GlideApp.with(context)
                .load(imgs.get(position).getImg().toString())
                .fitCenter()
                .override(1242,699)
                .into(img);

        view.addView(img,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}
