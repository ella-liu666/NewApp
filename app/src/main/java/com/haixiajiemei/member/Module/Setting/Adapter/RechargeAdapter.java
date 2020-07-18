package com.haixiajiemei.member.Module.Setting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haixiajiemei.member.Module.Setting.Model.Recharge;
import com.haixiajiemei.member.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.ViewHolder> {

    private ArrayList<Recharge> Recharge;
    private Context context;

    public RechargeAdapter(Context context, ArrayList<Recharge> Recharge) {
        this.Recharge = Recharge;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RechargeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.RechargeContent.setText(context.getResources().getString(R.string.Recharge_Content,Recharge.get(position).getPointChargeSource(),String.valueOf(Recharge.get(position).getChargeAmount())));
        holder.RechargeNum.setText("+"+Recharge.get(position).getPoint());
        holder.RechargeTime.setText(Recharge.get(position).getPointTradeTime());
    }

    @Override
    public int getItemCount() {
        return Recharge.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.RechargeContent)
        TextView RechargeContent;
        @BindView(R.id.RechargeNum)
        TextView RechargeNum;
        @BindView(R.id.RechargeTime)
        TextView RechargeTime;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
