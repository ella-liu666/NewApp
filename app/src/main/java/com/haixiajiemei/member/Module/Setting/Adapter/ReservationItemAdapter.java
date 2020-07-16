package com.haixiajiemei.member.Module.Setting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haixiajiemei.member.Module.Setting.Model.Reservation;
import com.haixiajiemei.member.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class ReservationItemAdapter extends RecyclerView.Adapter<ReservationItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Reservation> item;

    public ReservationItemAdapter(Context context, ArrayList<Reservation> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservationItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
