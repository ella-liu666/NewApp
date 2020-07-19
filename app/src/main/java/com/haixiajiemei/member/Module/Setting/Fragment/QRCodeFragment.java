package com.haixiajiemei.member.Module.Setting.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.haixiajiemei.member.Module.Setting.Contract.QRcodePointContract;
import com.haixiajiemei.member.Module.Setting.Presenter.QRcodePointPresenter;
import com.haixiajiemei.member.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;

public class QRCodeFragment extends Fragment implements QRcodePointContract.ViewAction {

    @BindView(R.id.txt_QRBalance)
    TextView txt_QRBalance;
    @BindView(R.id.imageView)
    ImageView imageView;

    private QRcodePointPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode, container, false);
        ButterKnife.bind(this, view);
        presenter = new QRcodePointPresenter(this, requireContext());
        presenter.doQRcodePoint();

        if (getArguments().getString("Balance") != null) {
            txt_QRBalance.setText(Html.fromHtml(getString(R.string.QRBalance, getArguments().getString("Balance"))));
        } else {
            txt_QRBalance.setText(Html.fromHtml(getString(R.string.QRBalance, String.valueOf(getArguments().getFloat("Balance")))));
        }

        return view;
    }

    @OnClick(R.id.btn_Recharge)
    public void onClick(View view) {
        AccountFragment accountFragment = new AccountFragment();
        switchFragmentToActivity(R.id.fragment_Introduction, accountFragment, requireActivity());
    }

    @Override
    public void QRcodePointSuccess(String s) {
        mHandler.postDelayed(() -> {
            BarcodeEncoder encoder = new BarcodeEncoder();
            try {
                Bitmap bit = encoder.encodeBitmap(s
                        , BarcodeFormat.QR_CODE, 1500, 1500);
                imageView.setImageBitmap(bit);
            } catch (WriterException e) {
                e.printStackTrace();
            }
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