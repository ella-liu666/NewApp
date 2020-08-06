package com.haixiajiemei.app.Module.Home.Adapter;

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
import com.haixiajiemei.app.Module.Home.Contract.ECProductCallback;
import com.haixiajiemei.app.Module.Home.Model.ItemsList;
import com.haixiajiemei.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ECProductAdapter extends RecyclerView.Adapter<ECProductAdapter.ViewHolder> {

    private List<ItemsList> items;
    private ECProductCallback callback;
    private Context context;

    public ECProductAdapter(List<ItemsList> items, ECProductCallback callback, Context context) {
        this.items = items;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ECProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != 0 && position % 2 != 0) {
            setVisibility(false, holder.layoutOuter);
            return;
        } else {
            GlideApp.with(context)
                    .load(items.get(position).getImage().toString())
                    .fitCenter()
                    .override(534, 300)
                    .into(holder.imgleft);

            holder.nameleft.setText(items.get(position).getName());
//            if(!BrandIntroductionItem.get(position).getTxt().equals("有机农场")){
//                holder.layoutleft.setOnClickListener(view -> callback.onBrandIntroClicked(position, BrandIntroductionItem.get(position).getId(), BrandIntroductionItem.get(position).getTxt()));
//            }


            GlideApp.with(context)
                    .load(items.get(position + 1).getImage().toString())
                    .fitCenter()
                    .override(534, 300)
                    .into(holder.imgright);

            holder.nameright.setText(items.get(position + 1).getName());
//            holder.layoutright.setOnClickListener(view -> callback.onBrandIntroClicked(position + 1, BrandIntroductionItem.get(position + 1).getId(), BrandIntroductionItem.get(position + 1).getTxt()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layoutOuter)
        LinearLayout layoutOuter;

        @BindView(R.id.layoutleft)
        LinearLayout layoutleft;
        @BindView(R.id.imgleft)
        ImageView imgleft;
        @BindView(R.id.nameleft)
        TextView nameleft;
        @BindView(R.id.leftProductPrice)
        TextView leftProductPrice;
        @BindView(R.id.leftAddShpping)
        ImageView leftAddShpping;


        @BindView(R.id.layoutright)
        LinearLayout layoutright;
        @BindView(R.id.imgright)
        ImageView imgright;
        @BindView(R.id.nameright)
        TextView nameright;
        @BindView(R.id.rightProductPrice)
        TextView rightProductPrice;
        @BindView(R.id.rightAddShpping)
        ImageView rightAddShpping;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setVisibility(boolean isVisible, View itemView) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }
}
