package com.haixiajiemei.app.Module.Order.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Order.Contract.OrderCallback;
import com.haixiajiemei.app.Module.Order.Contract.ShoppingCartItemCallback;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;
import com.haixiajiemei.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartItemAdapter extends RecyclerView.Adapter<ShoppingCartItemAdapter.ViewHolder> {

    public List<ShoppingCart> cart;
    private Context context;
    private ShoppingCartItemCallback callback;
    private OrderCallback orderCallback;
    private int Num;
    private List<String> name;
    private String Type;

    public ShoppingCartItemAdapter(List<ShoppingCart> cart, Context context, ShoppingCartItemCallback callback, String Type) {
        this.cart = cart;
        this.context = context;
        this.callback = callback;
        this.Type = Type;
    }

    public ShoppingCartItemAdapter(List<ShoppingCart> cart, Context context, String Type) {
        this.cart = cart;
        this.context = context;
        this.Type = Type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShoppingCartItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(Type.equals("ShoppingCart")){
            holder.Quantity.setVisibility(View.VISIBLE);
        }else {
            holder.Quantity.setVisibility(View.GONE);
        }

        GlideApp.with(context)
                .load(cart.get(position).image.toString())
                .override(200, 200)
                .fitCenter()
                .into(holder.item_img);
        holder.item_title.setText(cart.get(position).mealName);
        holder.num.setText(String.valueOf(cart.get(position).amount));
        float feedingPrice = 0;
        for (int i = 0; i < cart.get(position).feeding.size(); i++) {
            name = new ArrayList<>();
            name.add(cart.get(position).feeding.get(i).name);
            feedingPrice = feedingPrice + cart.get(position).feeding.get(i).price;
        }
        holder.item_price.setText("¥" + String.valueOf(cart.get(position).price + feedingPrice));
        holder.item_feeding.setText(String.valueOf(name));
        holder.less.setOnClickListener(view -> {
            Num = Integer.parseInt(holder.num.getText().toString());
            Num--;
            if (Num < 1) {
                Num = 1;
            }
            holder.num.setText(String.valueOf(Num));
            cart.get(position).amount = Num;
            callback.onRecyclerViewUpData(cart);
        });
        holder.plus.setOnClickListener(view -> {
            Num = Integer.parseInt(holder.num.getText().toString());

            Num++;
            holder.num.setText(String.valueOf(Num));
            cart.get(position).amount = Num;
            callback.onRecyclerViewUpData(cart);
        });

        holder.del.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.note);
            builder.setMessage(R.string.delete);
            builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
                List<ShoppingCart> toRemove = new ArrayList<>();
                for (ShoppingCart SC : cart) {
                    toRemove.add(cart.get(position));
                }
                cart.removeAll(toRemove);
                notifyDataSetChanged();
                callback.onRecyclerViewUpData(cart);
                orderCallback = (OrderCallback) context;
                orderCallback.onOrderCallback();
            });
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        });
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.del)
        ImageView del;
        @BindView(R.id.ShoppingItem)
        LinearLayout ShoppingItem;
        @BindView(R.id.item_img)
        ImageView item_img;
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_price)
        TextView item_price;
        @BindView(R.id.item_feeding)
        TextView item_feeding;
        @BindView(R.id.less)
        ImageView less;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.plus)
        ImageView plus;
        @BindView(R.id.Quantity)
        LinearLayout Quantity;


        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
