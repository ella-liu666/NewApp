package com.haixiajiemei.app.Module.Setting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.haixiajiemei.app.Module.Setting.Contract.CouponItemCallback;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;
import com.haixiajiemei.app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponItemAdapter extends RecyclerView.Adapter<CouponItemAdapter.ViewHolder> {

    private List<Coupon> item;
    private CouponItemCallback callback;
    private Context context;
    private String tag;

    public CouponItemAdapter(List<Coupon> item, CouponItemCallback callback, Context context,String tag) {
        this.item = item;
        this.callback = callback;
        this.context = context;
        this.tag=tag;
    }

    public CouponItemAdapter(List<Coupon> item,  Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public CouponItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CouponItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_unused_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CouponItemAdapter.ViewHolder holder, int position) {
        holder.denomination.setText(String.valueOf(item.get(position).getDenomination()));
        holder.storeName.setText(item.get(position).getName());
        holder.couponCategoryName.setText(item.get(position).getStoreName());
        holder.dueTime.setText(item.get(position).getDueTime());
        holder.Warning.setText(context.getString(R.string.Warning, item.get(position).getStoreName()));
        if(tag!=null){
            holder.coupon_item.setOnClickListener(view -> callback.onCouponItemClicked(item.get(position).getAccountCouponMapID(),
                    item.get(position).getDenomination(),item.get(position).getName(),item.get(position).getStoreName(),item.get(position).getDueTime()));
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.denomination)
        TextView denomination;
        @BindView(R.id.storeName)
        TextView storeName;
        @BindView(R.id.couponCategoryName)
        TextView couponCategoryName;
        @BindView(R.id.dueTime)
        TextView dueTime;
        @BindView(R.id.coupon_Warning)
        TextView Warning;
        @BindView(R.id.coupon_item)
        FrameLayout coupon_item;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
