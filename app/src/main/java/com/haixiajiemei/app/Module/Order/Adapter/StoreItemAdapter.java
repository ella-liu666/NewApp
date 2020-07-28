package com.haixiajiemei.app.Module.Order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemCallback;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;
import com.haixiajiemei.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.ViewHolder> {

    private List<StoreItem> items;
    private Context context;
    private StoreItemCallback callback;

    public StoreItemAdapter(List<StoreItem> items, Context context, StoreItemCallback callback) {
        this.items = items;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(items.get(position).getImage().toString())
                .override(200, 200)
                .fitCenter()
                .into(holder.item_img);
        holder.item_title.setText(items.get(position).getName());
        holder.item_detail.setText(items.get(position).getDetail());
        holder.item_price.setText("¥"+String.valueOf(items.get(position).getPrice()));
        holder.StoreItem.setOnClickListener(view -> callback.onSettingItemClicked(items.get(position).getId(),items.get(position).getDbName()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_img)
        ImageView item_img;
        @BindView(R.id.item_detail)
        TextView item_detail;
        @BindView(R.id.item_price)
        TextView item_price;
        @BindView(R.id.StoreItem)
        LinearLayout StoreItem;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
