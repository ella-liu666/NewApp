package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.haixiajiemei.app.Module.Setting.Adapter.CouponAdapter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.Util.CustomViewPager;

public class CouponFragment extends Fragment {
    @BindView(R.id.tablelayout)
    TabLayout tablelayout;
    @BindView(R.id.viewpage)
    CustomViewPager viewpage;

    private CouponAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> adapter.notifyDataSetChanged(),10);
    }

    private void init(){
        adapter=new CouponAdapter(getFragmentManager(), getResources().getStringArray(R.array.tab_coupon));
        viewpage.setAdapter(adapter);

        tablelayout.setupWithViewPager(viewpage);
        tablelayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewpage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewpage.setCurrentItem(tab.getPosition());
            }
        });
    }
}
