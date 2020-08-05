package com.haixiajiemei.app.Module.Order.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haixiajiemei.app.MainActivity;
import com.haixiajiemei.app.Module.Order.Adapter.ShoppingCartItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.ShoppingCartItemCallback;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCartList;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.SQLite.ShoppingCartDB;
import com.haixiajiemei.app.ToolBarActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.haixiajiemei.app.SQLite.ShoppingCartDB.GetShoppingCart;
import static com.haixiajiemei.app.Util.Proclaim.PAYMENTSCREEN;
import static com.haixiajiemei.app.Util.Proclaim.SHOPPINGCART;

public class ShoppingCartFragment extends Fragment implements ShoppingCartItemCallback {

    @BindView(R.id.store_infor)
    TextView store_infor;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.Sum)
    TextView Sum;
    @BindView(R.id.ShoppingCart)
    RecyclerView ShoppingCart;
    @BindView(R.id.NoData)
    TextView NoData;
    @BindView(R.id.information)
    LinearLayout information;

    private ShoppingCartItemAdapter adapter;
    private float total = 0;
    private ShoppingCartList shoppingCartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @OnClick(R.id.ToSettle)
    public void onClick(View view) {
        ShoppingCartList sd = GetShoppingCart(requireContext());

        Gson gson = new Gson();
        ShoppingCartList shoppingCartList = new ShoppingCartList();
        shoppingCartList.setcName(sd.getcName());
        shoppingCartList.setAddress(sd.getAddress());
        shoppingCartList.setStoreAccount(sd.getStoreAccount());
        shoppingCartList.setDbid(sd.getDbid());
        shoppingCartList.setTotal(total);
        shoppingCartList.cart = sd.cart;

        ShoppingCartDB.UpdateData(getContext(), gson.toJson(shoppingCartList));

        if (requireContext().getSharedPreferences("UserToken", MODE_PRIVATE).getBoolean("loginStatus", true)) {
            Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
            intent.putExtra("Type", PAYMENTSCREEN);
            intent.putExtra("title", getString(R.string.PaymentPage));
            startActivity(intent);

        } else {

            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.putExtra("Type", SHOPPINGCART);
            startActivity(intent);
        }
    }

    private void init() {
        ShoppingCartList sd = GetShoppingCart(requireContext());
        if (sd == null) {
            information.setVisibility(View.GONE);
            NoData.setVisibility(View.VISIBLE);
        } else {
            information.setVisibility(View.VISIBLE);
            NoData.setVisibility(View.GONE);
            store_infor.setText(sd.getcName());
            distance.setText(sd.getAddress());
            adapter = new ShoppingCartItemAdapter(sd.cart, requireContext(), this, "ShoppingCart");
            ShoppingCart.setLayoutManager(new LinearLayoutManager(requireContext()));
            ShoppingCart.setAdapter(adapter);
            for (int i = 0; i < sd.cart.size(); i++) {
                float sum = 0;
                for (int j = 0; j < sd.cart.get(i).feeding.size(); j++) {
                    sum = (sd.cart.get(i).price +
                            sd.cart.get(i).feeding.get(j).price) * sd.cart.get(i).amount;
                }
                total = total + sum;
            }
            Sum.setText(getString(R.string.Total, String.valueOf(total)));
        }
    }

    @Override
    public void onRecyclerViewUpData(List<ShoppingCart> cart) {
        Gson gson = new Gson();
        ShoppingCartList sd = GetShoppingCart(requireContext());
        float total = 0;
        if (cart.size() == 0) {
            ShoppingCartDB.DelData(getContext(), gson.toJson(sd));
            information.setVisibility(View.GONE);
            NoData.setVisibility(View.VISIBLE);
        } else {
            information.setVisibility(View.VISIBLE);
            NoData.setVisibility(View.GONE);
            store_infor.setText(sd.getcName());
            distance.setText(sd.getAddress());
            adapter = new ShoppingCartItemAdapter(cart, requireContext(), this, "ShoppingCart");
            ShoppingCart.setLayoutManager(new LinearLayoutManager(requireContext()));
            ShoppingCart.setAdapter(adapter);
            for (int i = 0; i < cart.size(); i++) {
                float sum = 0;
                for (int j = 0; j < cart.get(i).feeding.size(); j++) {
                    sum = (cart.get(i).price +
                            cart.get(i).feeding.get(j).price) * cart.get(i).amount;
                }
                total = total + sum;
            }
            Sum.setText(getString(R.string.Total, String.valueOf(total)));

            shoppingCartList = sd;
            sd.cart = cart;

            ShoppingCartDB.UpdateData(getContext(), gson.toJson(shoppingCartList));
        }
    }
}
