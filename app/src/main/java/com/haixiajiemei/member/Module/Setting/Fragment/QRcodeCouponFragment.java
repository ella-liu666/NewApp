package com.haixiajiemei.member.Module.Setting.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.haixiajiemei.member.Module.Setting.Contract.QRcodeCouponContract;
import com.haixiajiemei.member.Module.Setting.Presenter.QRcodeCouponPresenter;
import com.haixiajiemei.member.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRcodeCouponFragment extends Fragment implements QRcodeCouponContract.ViewAction {
    @BindView(R.id.txt_denomination)
    TextView txt_denomination;
    @BindView(R.id.txt_storeName)
    TextView txt_storeName;
    @BindView(R.id.txt_dueTime)
    TextView txt_dueTime;
    @BindView(R.id.imageView)
    ImageView imageView;

    private QRcodeCouponPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode_coupon, container, false);
        ButterKnife.bind(this, view);

        txt_denomination.setText(String.valueOf(getArguments().getFloat("denomination")));
        txt_storeName.setText(getArguments().getString("storeName"));
        txt_dueTime.setText(getArguments().getString("dueTime"));

        Log.e("444====","ee ="+getArguments().getInt("accountCouponMapID"));
        Log.e("555====","ee ="+getArguments().getFloat("denomination"));
        Log.e("666====","ee ="+getArguments().getString("name"));
        Log.e("777====","ee ="+getArguments().getString("storeName"));
        Log.e("888====","ee ="+getArguments().getString("dueTime"));
        presenter=new QRcodeCouponPresenter(this,requireContext());
        presenter.doQRcodeCoupon(requireContext(),getArguments().getInt("accountCouponMapID")
                ,getArguments().getFloat("denomination"),getArguments().getString("name"),
                getArguments().getString("storeName"),getArguments().getString("dueTime"));

        return view;
    }

    @Override
    public void QRcodeCouponSuccess(String s) {
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
