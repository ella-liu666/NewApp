package com.haixiajiemei.app.Module.Home.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.haixiajiemei.app.Module.Account.Fragment.LoginFragment;
import com.haixiajiemei.app.Module.Home.Adapter.AdvertisingAdapter;
import com.haixiajiemei.app.Module.Home.Adapter.BrandIntroAdapter;
import com.haixiajiemei.app.Module.Home.Contract.BrandIntroCallback;
import com.haixiajiemei.app.Module.Home.Contract.HomeAdImgContract;
import com.haixiajiemei.app.Module.Home.Contract.HomeStoreImgContract;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Module.Home.Present.HomeAdImgPresenter;
import com.haixiajiemei.app.Module.Home.Present.HomeStoreImgPresenter;
import com.haixiajiemei.app.Module.Setting.Contract.PointContract;
import com.haixiajiemei.app.Module.Setting.Present.PointPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.haixiajiemei.app.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.app.Util.Proclaim.INTRODUCTION;
import static com.haixiajiemei.app.Util.Proclaim.QRCODE;

public class HomeFragment extends Fragment implements HomeStoreImgContract.ViewAction, HomeAdImgContract.ViewAction, BrandIntroCallback, PointContract.ViewAction {

    @BindView(R.id.rollpagerview)
    RollPagerView rollPagerView;
    @BindView(R.id.BrandIntroductionGroup)
    RecyclerView BrandIntroductionGroup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private HomeStoreImgPresenter presenter;
    private HomeAdImgPresenter Adpresenter;
    private PointPresenter PointPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ArrayList<ImgAndTxt> BrandIntroductionItem = new ArrayList<>();
    private ArrayList<ImgAndTxt> AdIntroductionItem = new ArrayList<>();
    private BrandIntroAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        Adpresenter = new HomeAdImgPresenter(this, requireContext());
        Adpresenter.doHomeAdImg();
        presenter = new HomeStoreImgPresenter(this, requireContext());
        presenter.doHomeStoreImg();

        if (requireContext().getSharedPreferences("Alert", MODE_PRIVATE).getBoolean("Terms", true)) {
            AlertDialogTool();
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        return view;
    }

    private void init() {
        //播放間隔
        rollPagerView.setPlayDelay(3000);
        //透明度
        rollPagerView.setAnimationDurtion(500);
        rollPagerView.setAdapter(new AdvertisingAdapter(requireContext(), AdIntroductionItem));
        rollPagerView.setHintView(new ColorPointHintView(getContext(), Color.RED, Color.WHITE));

    }

    @OnClick(R.id.qrcode)
    public void onClick(View view) {
        if (requireActivity().getSharedPreferences("UserToken", MODE_PRIVATE).getBoolean("loginStatus", true)) {
            PointPresenter = new PointPresenter(this, requireContext());
            PointPresenter.doPoint();
        } else {
            LoginFragment loginFragment = new LoginFragment();
            switchFragmentToActivity(R.id.fragment_container, loginFragment, requireActivity());
        }
    }

    @Override
    public void HomeStoreImgContractSuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            BrandIntroductionItem.clear();
            BrandIntroductionItem.addAll(imgAndTxt);

            adapter = new BrandIntroAdapter(requireContext(), BrandIntroductionItem, HomeFragment.this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            BrandIntroductionGroup.setLayoutManager(layoutManager);
            BrandIntroductionGroup.setAdapter(adapter);
        }, 1);

    }

    @Override
    public void HomeAdImgContractSuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            AdIntroductionItem.clear();
            AdIntroductionItem.addAll(imgAndTxt);

            init();
        }, 1);

    }

    @Override
    public void PointSuccess(String s) {
        mHandler.postDelayed(() -> {
            Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
            intent.putExtra("Type", QRCODE);
            intent.putExtra("title", R.string.Payment_QR_code);
            intent.putExtra("Balance", s);
            startActivity(intent);
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
            SharedPreferences pref = requireContext().getSharedPreferences("UserToken", MODE_PRIVATE);
            pref.edit()
                    .putString("access_token", Access_token)
                    .commit();

            Adpresenter = new HomeAdImgPresenter(this, requireContext());
            Adpresenter.doHomeAdImg();
            presenter = new HomeStoreImgPresenter(this, requireContext());
            presenter.doHomeStoreImg();

            if (requireContext().getSharedPreferences("Alert", MODE_PRIVATE).getBoolean("Terms", true)) {
                AlertDialogTool();
            }

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        }, 1);
    }

    @Override
    public void onBrandIntroClicked(int position, int id, String title) {
        Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("Type", INTRODUCTION);
        startActivity(intent);
    }

    private void AlertDialogTool() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.terms, null);
        WebView webView = (WebView) view.findViewById(R.id.web);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://member.haixiajiemei.com/MainSite/LegalAgreement.html");
        builder.setView(view);
        builder.setPositiveButton(R.string.agree, (dialog, which) -> {
            SharedPreferences pref = requireContext().getSharedPreferences("Alert", MODE_PRIVATE);
            pref.edit()
                    .putBoolean("Terms", false)
                    .commit();

            dialog.dismiss();
            Field field = null;
            try {
                field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                field.setAccessible(true);//設定該屬性可以訪問
            } catch (Exception ex) {
            }
            try {
                field.set(dialog, true);
                dialog.dismiss();
            } catch (Exception ex) {
            }
        });
        builder.setNegativeButton(R.string.disagree, (dialog, which) -> {
            requireActivity().finish();
        });

        builder.setNeutralButton(R.string.back, (dialog, which) -> {
            webView.goBack();
            Field field = null;
            try {
                field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                field.setAccessible(true);//設定該屬性可以訪問
            } catch (Exception ex) {
            }
            try {
                field.set(dialog, false);
                dialog.dismiss();
            } catch (Exception ex) {
            }

        });

        builder.setOnKeyListener((dialog, keyCode, event) -> {
            webView.goBack();
            return true;
        });
        builder.setCancelable(false);
        builder.show();

    }
}
