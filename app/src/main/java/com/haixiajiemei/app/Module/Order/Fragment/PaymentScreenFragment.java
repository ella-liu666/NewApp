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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haixiajiemei.app.MainActivity;
import com.haixiajiemei.app.Module.Order.Adapter.ShoppingCartItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.Cartcontract;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCartList;
import com.haixiajiemei.app.Module.Order.Present.CartPresenter;
import com.haixiajiemei.app.Module.Setting.Contract.PointContract;
import com.haixiajiemei.app.Module.Setting.Present.PointPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.SQLite.ShoppingCartDB;
import com.haixiajiemei.app.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.SQLite.ShoppingCartDB.GetShoppingCart;
import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.Proclaim.ADDRESS;
import static com.haixiajiemei.app.Util.Proclaim.CARTSUCCESS;

public class PaymentScreenFragment extends Fragment implements PointContract.ViewAction , Cartcontract.ViewAction{
    @BindView(R.id.StoreName)
    TextView StoreName;
    @BindView(R.id.Total)
    TextView Total;
    @BindView(R.id.Pickup_method)
    RadioGroup Pickup_method;
    @BindView(R.id.SelfMention)
    RadioButton SelfMention;
    @BindView(R.id.delivery)
    RadioButton delivery;
    @BindView(R.id.ShoppingCart)
    RecyclerView ShoppingCart;
    @BindView(R.id.Address)
    TextView Address;
    @BindView(R.id.PhoneName)
    TextView PhoneName;

    private PointPresenter PointPresenter;
    private CartPresenter cartPresenter;
    private ShoppingCartItemAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson = new Gson();
    private ShoppingCartList sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_screen, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        if (getArguments() != null) {
            Address.setText(getArguments().getString("Address"));
            PhoneName.setText(getArguments().getString("PhoneName"));
            delivery.setChecked(true);
        }

        sd = GetShoppingCart(requireContext());
        StoreName.setText(sd.getcName() + getString(R.string.TakeawayOrder));
        Total.setText("¥" + String.valueOf(sd.getTotal()));

        adapter = new ShoppingCartItemAdapter(sd.cart, requireContext(), "PaymentScreen");
        ShoppingCart.setLayoutManager(new LinearLayoutManager(requireContext()));
        ShoppingCart.setAdapter(adapter);
    }

    @OnClick({R.id.Payment, R.id.delivery, R.id.SelfMention})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Payment:
                PointPresenter = new PointPresenter(this, requireContext());
                PointPresenter.doPoint();
                break;
            case R.id.delivery:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", ADDRESS);
                intent.putExtra("title", getString(R.string.MyAddress));
                startActivity(intent);
                break;
            case R.id.SelfMention:
                Address.setText("");
                PhoneName.setText("");
                break;
        }
    }

    @Override
    public void PointSuccess(String s) {
        mHandler.postDelayed(() -> {
            if (Float.parseFloat(s) < sd.getTotal()) {
                CreateAlertDialogTool(requireContext(), R.string.note, R.string.PointInsufficient);
            } else {
                ShoppingCartList sd = GetShoppingCart(requireContext());
                cartPresenter = new CartPresenter(this, requireContext(), sd.getStoreAccount(), sd.getTotal(), ShoppingCartList.delType, sd.cart);
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
                ShoppingCartDB.DelData(getContext(), gson.toJson(sd));
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
