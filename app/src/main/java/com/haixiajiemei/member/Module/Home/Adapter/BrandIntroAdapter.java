package com.haixiajiemei.member.Module.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haixiajiemei.member.Helper.GlideApp;
import com.haixiajiemei.member.Module.Home.Contract.BrandIntroCallback;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.member.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandIntroAdapter extends RecyclerView.Adapter<BrandIntroAdapter.ViewHolder> {

    private ArrayList<ImgAndTxt> BrandIntroductionItem;
    private BrandIntroCallback callback;
    private Context context;

    public BrandIntroAdapter(Context context, ArrayList<ImgAndTxt> brandIntroductionItem, BrandIntroCallback callback) {
        this.BrandIntroductionItem = brandIntroductionItem;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new BrandIntroAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.brandintr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != 0 && position % 2 != 0) {
            setVisibility(false, holder.layoutOuter);
            return;
        } else {
            GlideApp.with(context)
                    .load(BrandIntroductionItem.get(position).getImg().toString())
                    .fitCenter()
                    .override(534, 300)
                    .into(holder.imgleft);

            holder.nameleft.setText(BrandIntroductionItem.get(position).getTxt());
            holder.layoutleft.setOnClickListener(view -> callback.onBrandIntroClicked(position, BrandIntroductionItem.get(position).getId(), BrandIntroductionItem.get(position).getTxt()));

            GlideApp.with(context)
                    .load(BrandIntroductionItem.get(position + 1).getImg().toString())
                    .fitCenter()
                    .override(534, 300)
                    .into(holder.imgright);

            holder.nameright.setText(BrandIntroductionItem.get(position + 1).getTxt());
            holder.layoutright.setOnClickListener(view -> callback.onBrandIntroClicked(position + 1, BrandIntroductionItem.get(position + 1).getId(), BrandIntroductionItem.get(position + 1).getTxt()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return BrandIntroductionItem.size();
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

        @BindView(R.id.layoutright)
        LinearLayout layoutright;
        @BindView(R.id.imgright)
        ImageView imgright;
        @BindView(R.id.nameright)
        TextView nameright;

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
