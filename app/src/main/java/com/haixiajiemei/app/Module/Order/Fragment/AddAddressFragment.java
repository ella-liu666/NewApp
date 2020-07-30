package com.haixiajiemei.app.Module.Order.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.haixiajiemei.app.Module.Order.Contract.InsertDeliveryListContract;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;
import com.haixiajiemei.app.Module.Order.Present.InsertDeliveryListPresenter;
import com.haixiajiemei.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;

public class AddAddressFragment extends Fragment implements InsertDeliveryListContract.ViewAction {
    @BindView(R.id.edit_Receiver)
    EditText edit_Receiver;
    @BindView(R.id.mister)
    RadioButton mister;
    @BindView(R.id.madam)
    RadioButton madam;
    @BindView(R.id.edit_phone_num)
    EditText edit_phone_num;
    @BindView(R.id.edit_Address)
    EditText edit_Address;

    private InsertDeliveryListPresenter insertDeliveryListPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

    }

    @OnClick(R.id.SaveAddress)
    public void onClick(View view) {
        if ("".equals(edit_Receiver.getText().toString()) | "".equals(edit_phone_num.getText().toString()) | "".equals(edit_Address.getText().toString())) {
            CreateAlertDialogTool(requireContext(), R.string.note, R.string.not_complete);
        } else {
            if (mister.isChecked()) {
                gender = getString(R.string.mister);
            } else if (madam.isChecked()) {
                gender = getString(R.string.madam);
            }
            insertDeliveryListPresenter = new InsertDeliveryListPresenter(this, requireContext(),
                    edit_Receiver.getText().toString(), gender, edit_phone_num.getText().toString(),
                    edit_Address.getText().toString(), "");
            insertDeliveryListPresenter.doInsertDeliveryList();
        }

    }

    @Override
    public void InsertDeliveryListSuccess(SuccessMessage successMessage) {
        mHandler.postDelayed(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("");
            builder.setMessage(successMessage.getMessage());
            builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
                getActivity().onBackPressed();
                dialog.dismiss();
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
