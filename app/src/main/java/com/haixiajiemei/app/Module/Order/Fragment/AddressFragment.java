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

import com.haixiajiemei.app.Module.Order.Adapter.AddressItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.AddressItemCallback;
import com.haixiajiemei.app.Module.Order.Contract.DeleteDeliveryAddressContract;
import com.haixiajiemei.app.Module.Order.Contract.DeliveryListContract;
import com.haixiajiemei.app.Module.Order.Model.Address;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;
import com.haixiajiemei.app.Module.Order.Present.DeleteDeliveryAddressPresenter;
import com.haixiajiemei.app.Module.Order.Present.DeliveryListPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.Proclaim.ADDADDRESS;
import static com.haixiajiemei.app.Util.Proclaim.PAYMENTSCREEN;

public class AddressFragment extends Fragment implements DeliveryListContract.ViewAction, AddressItemCallback, DeleteDeliveryAddressContract.ViewAction {

    @BindView(R.id.AddressList)
    RecyclerView AddressList;
    @BindView(R.id.NoData)
    LinearLayout NoData;

    private DeliveryListPresenter deliveryListPresenter;
    private DeleteDeliveryAddressPresenter deleteDeliveryAddressPresenter;
    private AddressItemAdapter addressItemAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        deliveryListPresenter = new DeliveryListPresenter(this, requireContext());
        deliveryListPresenter.doDeliveryList();
    }

    @OnClick(R.id.AddAddress)
    public void onClick(View view) {
        Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
        intent.putExtra("Type", ADDADDRESS);
        intent.putExtra("title", getString(R.string.AddAddress));
        startActivity(intent);
    }

    @Override
    public void DeliveryListSuccess(List<Address> DeliveryList) {
        mHandler.postDelayed(() -> {
            if (DeliveryList.size() != 0) {
                NoData.setVisibility(View.GONE);
                AddressList.setVisibility(View.VISIBLE);
            } else {
                NoData.setVisibility(View.VISIBLE);
                AddressList.setVisibility(View.GONE);
            }
            addressItemAdapter = new AddressItemAdapter(DeliveryList, requireContext(), this);
            AddressList.setLayoutManager(new LinearLayoutManager(requireContext()));
            AddressList.setAdapter(addressItemAdapter);
        }, 1);
    }

    @Override
    public void DeleteDeliveryAddressSuccess(SuccessMessage successMessage) {
        mHandler.postDelayed(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("");
            builder.setMessage(successMessage.getMessage());
            builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
                dialog.dismiss();
                init();
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

    @Override
    public void onAddressItemClicked(String Address, String PhoneName) {
        Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
        intent.putExtra("Type", PAYMENTSCREEN);
        intent.putExtra("title", getString(R.string.PaymentPage));
        intent.putExtra("Address", Address);
        intent.putExtra("PhoneName", PhoneName);
        startActivity(intent);
    }

    @Override
    public void onDelAddressItemClicked(int deliveryID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.note);
        builder.setMessage(R.string.delete);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            dialog.dismiss();
            deleteDeliveryAddressPresenter = new DeleteDeliveryAddressPresenter(this, requireContext(), deliveryID);
            deleteDeliveryAddressPresenter.doDeleteDeliveryAddress();
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
