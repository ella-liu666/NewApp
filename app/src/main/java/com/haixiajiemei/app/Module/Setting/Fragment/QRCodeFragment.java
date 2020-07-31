package com.haixiajiemei.app.Module.Setting.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.constraintlayout.solver.state.State;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.haixiajiemei.app.Module.Setting.Contract.QRcodePointContract;
import com.haixiajiemei.app.Module.Setting.Present.QRcodePointPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Calendar;

import static com.haixiajiemei.app.Util.Proclaim.ACCOUNT;

public class QRCodeFragment extends Fragment implements QRcodePointContract.ViewAction {

    @BindView(R.id.txt_QRBalance)
    TextView txt_QRBalance;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_CountDownTimer)
    TextView txt_CountDownTimer;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private QRcodePointPresenter presenter;
    private CountDownTimer QRCountDownTimer;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static String stringRemainTimer = "00:00:00";

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

    @OnClick({R.id.btn_Recharge, R.id.CountDownTimer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Recharge:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", ACCOUNT);
                if (getArguments().getString("Balance") != null) {
                    intent.putExtra("Balance", getArguments().getString("Balance"));
                } else {
                    intent.putExtra("Balance", getArguments().getFloat("Balance"));
                }
                startActivity(intent);
                requireActivity().finish();
                break;
            case R.id.CountDownTimer:
                presenter = new QRcodePointPresenter(this, requireContext());
                presenter.doQRcodePoint();
                break;
        }

    }

    @Override
    public void QRcodePointSuccess(String s) {
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
    public void showProgress() {
        mHandler.postDelayed(() -> {
            requireActivity().runOnUiThread(() -> {
                progressBar.setVisibility(View.VISIBLE);
                requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            });
        }, 1);
    }

    @Override
    public void hideProgress() {
        mHandler.postDelayed(() -> {
            requireActivity().runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            });
        }, 1);
    }

    @Override
    public void errorOccurred(String reason) {

    }

    @Override
    public void ApierrorOccurred(String Access_token) {
        mHandler.postDelayed(() -> {
        }, 1);
    }

    private void CountdownTimer(int finalSeconds) {
        if (QRCountDownTimer != null) {
            QRCountDownTimer.cancel();
        }
        QRCountDownTimer = new CountDownTimer((finalSeconds * 1000), 1000) {

            @Override
            public void onFinish() {
                presenter = new QRcodePointPresenter(QRCodeFragment.this, requireContext());
                presenter.doQRcodePoint();
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
