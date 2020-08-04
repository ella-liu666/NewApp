package com.haixiajiemei.app.Module.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haixiajiemei.app.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> categoryList;

    public SpinnerAdapter(Context mContext, ArrayList<String> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.spinner_item, viewGroup, false);
        TextView itemTxt = view.findViewById(R.id.itemName);
        itemTxt.setText(categoryList.get(position));
        return view;
    }
}
