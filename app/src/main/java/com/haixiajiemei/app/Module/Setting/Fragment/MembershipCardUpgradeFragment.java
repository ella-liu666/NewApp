package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.haixiajiemei.app.Module.Setting.Adapter.MembershipCardAdapter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.Util.CustomViewPager;

public class MembershipCardUpgradeFragment extends Fragment {
    @BindView(R.id.tablelayout)
    TabLayout tablelayout;
    @BindView(R.id.viewpage)
    CustomViewPager viewpage;

    private MembershipCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_membership_card_upgrade, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> adapter.notifyDataSetChanged(),10);
    }

    private void init() {
        adapter= new MembershipCardAdapter(getFragmentManager(), getResources().getStringArray(R.array.tab_membership_card));
        viewpage.setAdapter(adapter);
        if (getArguments().getString("title") != null && getArguments().getString("title").equals(getString(R.string.MembershipCard))) {
            viewpage.setCurrentItem(1);
        }

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
