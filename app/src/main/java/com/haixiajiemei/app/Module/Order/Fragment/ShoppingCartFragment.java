package com.haixiajiemei.app.Module.Order.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haixiajiemei.app.MainActivity;
import com.haixiajiemei.app.Module.Order.Adapter.ShoppingCartItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.Cartcontract;
import com.haixiajiemei.app.Module.Order.Contract.ShoppingCartItemCallback;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCartList;
import com.haixiajiemei.app.Module.Order.Present.CartPresenter;
import com.haixiajiemei.app.Module.Setting.Contract.PointContract;
import com.haixiajiemei.app.Module.Setting.Present.PointPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.SQLite.ShoppingCartDB;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.haixiajiemei.app.SQLite.ShoppingCartDB.GetShoppingCart;
import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.Proclaim.CARTSUCCESS;
import static com.haixiajiemei.app.Util.Proclaim.SHOPPINGCART;

public class ShoppingCartFragment extends Fragment implements ShoppingCartItemCallback, PointContract.ViewAction, Cartcontract.ViewAction {

    @BindView(R.id.store_infor)
    TextView store_infor;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.Sum)
    TextView Sum;
    @BindView(R.id.Pickup_method)
    RadioGroup Pickup_method;
    @BindView(R.id.SelfMention)
    RadioButton SelfMention;
    @BindView(R.id.delivery)
    RadioButton delivery;
    @BindView(R.id.ShoppingCart)
    RecyclerView ShoppingCart;
    @BindView(R.id.NoData)
    TextView NoData;
    @BindView(R.id.information)
    LinearLayout information;

    private ShoppingCartItemAdapter adapter;
    private PointPresenter PointPresenter;
    private CartPresenter cartPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private float total = 0;
    private Gson gson = new Gson();
    private ShoppingCartList shoppingCartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @OnClick(R.id.ToSettle)
    public void onClick(View view) {
        ShoppingCartList sd = GetShoppingCart(requireContext());
        if (requireContext().getSharedPreferences("UserToken", MODE_PRIVATE).getBoolean("loginStatus", true)) {
            if(delivery.isChecked()){

            }
            PointPresenter = new PointPresenter(this, requireContext());
            PointPresenter.doPoint();
        } else {
            Gson gson = new Gson();
            ShoppingCartList shoppingCartList = new ShoppingCartList();
            shoppingCartList.setcName(sd.getcName());
            shoppingCartList.setAddress(sd.getAddress());
            shoppingCartList.setStoreAccount(sd.getStoreAccount());
            shoppingCartList.setDbid(sd.getDbid());
            shoppingCartList.setTotal(total);
            shoppingCartList.cart = sd.cart;

            ShoppingCartDB.UpdateData(getContext(), gson.toJson(shoppingCartList));

            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.putExtra("Type", SHOPPINGCART);
            startActivity(intent);
        }
    }

    private void init() {
        ShoppingCartList sd = GetShoppingCart(requireContext());
        if (sd != null) {
            information.setVisibility(View.VISIBLE);
            NoData.setVisibility(View.GONE);
            store_infor.setText(sd.getcName());
            distance.setText(sd.getAddress());
            adapter = new ShoppingCartItemAdapter(sd.cart, requireContext(), this);
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
        } else {
            information.setVisibility(View.GONE);
            NoData.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onRecyclerViewUpData(List<ShoppingCart> cart) {
        Gson gson = new Gson();
        ShoppingCartList sd = GetShoppingCart(requireContext());
        float total = 0;
        information.setVisibility(View.VISIBLE);
        NoData.setVisibility(View.GONE);
        store_infor.setText(sd.getcName());
        distance.setText(sd.getAddress());
        adapter = new ShoppingCartItemAdapter(cart, requireContext(), this);
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

    @Override
    public void PointSuccess(String s) {
        mHandler.postDelayed(() -> {
            if (Float.parseFloat(s) < total) {
                CreateAlertDialogTool(requireContext(), R.string.note, R.string.PointInsufficient);
            } else {
                ShoppingCartList sd = GetShoppingCart(requireContext());
                cartPresenter = new CartPresenter(this, requireContext(), sd.getStoreAccount(), total, ShoppingCartList.delType, sd.cart);
                cartPresenter.doCart();
            }
        }, 1);
    }

    @Override
    public void CartSuccess(String s) {
        mHandler.postDelayed(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("");
            builder.setMessage(s);
            builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
                dialog.dismiss();
                ShoppingCartDB.DelData(getContext(), gson.toJson(shoppingCartList));
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                intent.putExtra("Type", CARTSUCCESS);
                startActivity(intent);
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }, 1);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void errorOccurred(String reason) {

    }
}
