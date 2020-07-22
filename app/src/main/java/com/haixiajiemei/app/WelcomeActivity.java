package com.haixiajiemei.app;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import com.haixiajiemei.app.Module.Account.Contract.VisitorsContract;
import com.haixiajiemei.app.Module.Account.Present.VisitorsPresenter;

import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeActivity extends AppCompatActivity implements VisitorsContract.ViewAction {

    private VisitorsPresenter visitorsPresenter;
    public static ArrayList<String> name;
    public static ArrayList<String> name2;
    public static ArrayList<Integer> img;
    public static ArrayList<Integer> img2;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        if ("".equals(getSharedPreferences("UserToken", MODE_PRIVATE).getString("access_token",""))) {
            visitorsPresenter = new VisitorsPresenter(this, this, "kunchiguest");
            visitorsPresenter.doVisitors();
        }else{
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }

        setting_item_init();
    }

    private void setting_item_init() {
        name = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.txt_setting_item)));
        img = new ArrayList<>();
        img.add(R.drawable.my_news);
        img.add(R.drawable.my_reservation);
        img.add(R.drawable.my_release);
        img.add(R.drawable.my_order);
        img.add(R.drawable.expenses_record);
        img.add(R.drawable.distribution);
        img.add(R.drawable.membership_card);

        name2 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.txt_membership_item)));
        img2 = new ArrayList<>();
        img2.add(R.drawable.terms);
        img2.add(R.drawable.privacy);
    }

    @Override
    public void VisitorsSuccess() {
        mHandler.postDelayed(() -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }, 1);
    }

    @Override
    public void showProgress() {
        runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideProgress() {
        runOnUiThread(() -> progressBar.setVisibility(View.GONE));
    }

    @Override
    public void errorOccurred(String reason) {

    }
}
