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

import com.haixiajiemei.app.Module.Order.Contract.AddressItemCallback;
import com.haixiajiemei.app.Module.Order.Model.Address;
import com.haixiajiemei.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressItemAdapter extends RecyclerView.Adapter<AddressItemAdapter.ViewHolder> {

    private List<Address> items;
    private Context context;
    private AddressItemCallback callback;

    public AddressItemAdapter(List<Address> items, Context context, AddressItemCallback callback) {
        this.items = items;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddressItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Address.setText(items.get(position).getAddress());
        holder.PhoneName.setText(items.get(position).getTelephone() + "  " + items.get(position).getName().charAt(0) + items.get(position).getGender());
        holder.DelAddress.setOnClickListener(view -> callback.onDelAddressItemClicked(items.get(position).getId()));
        holder.Item.setOnClickListener(view -> callback.onAddressItemClicked(holder.Address.getText().toString(),holder.PhoneName.getText().toString(),items.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.DelAddress)
        ImageView DelAddress;
        @BindView(R.id.Address)
        TextView Address;
        @BindView(R.id.PhoneName)
        TextView PhoneName;
        @BindView(R.id.Item)
        LinearLayout Item;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
