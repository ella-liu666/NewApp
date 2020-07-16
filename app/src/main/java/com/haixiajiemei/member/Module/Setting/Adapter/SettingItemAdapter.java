package com.haixiajiemei.member.Module.Setting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haixiajiemei.member.Helper.GlideApp;
import com.haixiajiemei.member.Module.Setting.Contract.SettingItemCallback;
import com.haixiajiemei.member.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingItemAdapter extends RecyclerView.Adapter<SettingItemAdapter.ViewHolder> {

    private ArrayList<String> name;
    private ArrayList<Integer> img;
    private SettingItemCallback callback;
    private Context context;

    public SettingItemAdapter(Context context, ArrayList<String> name, ArrayList<Integer> img, SettingItemCallback callback) {
        this.name = name;
        this.img = img;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SettingItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(img.get(position))
                .override(54, 52)
                .fitCenter()
                .into(holder.itemimg);
//        holder.itemimg.setImageResource(img.get(position));
        holder.txt_title.setText(name.get(position));
        holder.Item.setOnClickListener(v -> callback.onSettingItemClicked(position, name.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Item)
        LinearLayout Item;
        @BindView(R.id.itemimg)
        ImageView itemimg;
        @BindView(R.id.txt_title)
        TextView txt_title;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
