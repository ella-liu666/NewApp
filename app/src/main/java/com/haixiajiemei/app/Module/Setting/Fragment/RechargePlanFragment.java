package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.haixiajiemei.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargePlanFragment extends Fragment {
    @BindView(R.id.settingweb)
    WebView settingweb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge_plan, container, false);
        ButterKnife.bind(this, view);

        if (getArguments().getString("title").equals(getString(R.string.Privacy))) {
            settingweb.setWebViewClient(new WebViewClient());
            settingweb.loadUrl("https://member.haixiajiemei.com/MainSite/LegalPolicy.html");
        }else  if (getArguments().getString("title").equals(getString(R.string.Terms))){
            settingweb.setWebViewClient(new WebViewClient());
            settingweb.loadUrl("https://member.haixiajiemei.com/MainSite/LegalContract.html");
        }else {
            settingweb.setWebViewClient(new WebViewClient());
            settingweb.loadUrl("https://member.haixiajiemei.com/Wallet/RechargePlan.html");
        }
        return view;
    }

}
