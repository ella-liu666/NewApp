package com.haixiajiemei.app.Module.Setting.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.haixiajiemei.app.Module.Setting.Fragment.AllOrderFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.BeShippedFragment;

public class MyOrderAdapter extends FragmentPagerAdapter {

    private String[] item;

    public MyOrderAdapter(FragmentManager supportFragmentManager, String[] item) {
        super(supportFragmentManager);
        this.item = item;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BeShippedFragment();
            case 1:
                return new AllOrderFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return item.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return item[position];
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
