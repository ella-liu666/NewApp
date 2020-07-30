package com.haixiajiemei.app.Module.Setting.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.haixiajiemei.app.Module.Setting.Contract.PointContract;
import com.haixiajiemei.app.Module.Setting.Contract.QRcodeCouponContract;
import com.haixiajiemei.app.Module.Setting.Present.PointPresenter;
import com.haixiajiemei.app.Module.Setting.Present.QRcodeCouponPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static com.haixiajiemei.app.Util.Proclaim.QRCODE;

public class QRcodeCouponFragment extends Fragment implements QRcodeCouponContract.ViewAction, PointContract.ViewAction {
    @BindView(R.id.txt_denomination)
    TextView txt_denomination;
    @BindView(R.id.txt_storeName)
    TextView txt_storeName;
    @BindView(R.id.txt_dueTime)
    TextView txt_dueTime;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_CountDownTimer)
    TextView txt_CountDownTimer;
    @BindView(R.id.imageView)
    ImageView imageView;

    private QRcodeCouponPresenter presenter;
    private PointPresenter PointPresenter;
    private CountDownTimer QRCountDownTimer;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String Balance;
    private static String stringRemainTimer = "00:00:00";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode_coupon, container, false);
        ButterKnife.bind(this, view);
        PointPresenter = new PointPresenter(this, requireContext());
        PointPresenter.doPoint();

        txt_denomination.setText(String.valueOf(getArguments().getFloat("denomination")));
        txt_storeName.setText(getArguments().getString("storeName"));
        txt_dueTime.setText(getArguments().getString("dueTime"));
        txt_name.setText(getArguments().getString("name"));


        presenter = new QRcodeCouponPresenter(this, requireContext());
        presenter.doQRcodeCoupon(requireContext(), getArguments().getInt("accountCouponMapID")
                , getArguments().getFloat("denomination"), getArguments().getString("name"),
                getArguments().getString("storeName"), getArguments().getString("dueTime"));

        return view;
    }

    @OnClick({R.id.checkout, R.id.CountDownTimer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkout:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", QRCODE);
                intent.putExtra("title", R.string.Payment_QR_code);
                intent.putExtra("Balance", Balance);
                startActivity(intent);
                requireActivity().finish();
                break;
            case R.id.CountDownTimer:
                presenter = new QRcodeCouponPresenter(this, requireContext());
                presenter.doQRcodeCoupon(requireContext(), getArguments().getInt("accountCouponMapID")
                        , getArguments().getFloat("denomination"), getArguments().getString("name"),
                        getArguments().getString("storeName"), getArguments().getString("dueTime"));
                break;
        }

    }

    @Override
    public void QRcodeCouponSuccess(String s) {
        mHandler.postDelayed(() -> {
            BarcodeEncoder encoder = new BarcodeEncoder();
            try {
                Bitmap bit = encoder.encodeBitmap(s
                        , BarcodeFormat.QR_CODE, 1000, 1000);
                imageView.setImageBitmap(bit);
                CountdownTimer(5 * 60);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }, 1);
    }

    @Override
    public void PointSuccess(String s) {
        mHandler.postDelayed(() -> {
            Balance = s;
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

    private void CountdownTimer(int finalSeconds) {
        if (QRCountDownTimer != null) {
            QRCountDownTimer.cancel();
        }
        QRCountDownTimer = new CountDownTimer((finalSeconds * 1000), 1000) {

            @Override
            public void onFinish() {
                presenter = new QRcodeCouponPresenter(QRcodeCouponFragment.this, requireContext());
                presenter.doQRcodeCoupon(requireContext(), getArguments().getInt("accountCouponMapID")
                        , getArguments().getFloat("denomination"), getArguments().getString("name"),
                        getArguments().getString("storeName"), getArguments().getString("dueTime"));
            }

            @Override
            public void onTick(long millisUntilFinished) {
                long mins = (millisUntilFinished / 1000) / 60;
                long secs = (millisUntilFinished / 1000) % 60;
                stringRemainTimer = mins + ":" + secs;

                txt_CountDownTimer.setText(stringRemainTimer);
            }

        }.start();

    }
}
