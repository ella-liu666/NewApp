package com.haixiajiemei.member.Module.Setting.Adapter;

import android.view.ViewGroup;

import com.haixiajiemei.member.Module.Setting.Fragment.CouponExpiredFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.CouponUnusedFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.CouponUsedFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CouponAdapter extends FragmentPagerAdapter {

    private String[] item;

    public CouponAdapter(FragmentManager supportFragmentManager, String[] item) {
        super(supportFragmentManager);
        this.item = item;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CouponUnusedFragment();
            case 1:
                return new CouponUsedFragment();
            case 2:
                return new CouponExpiredFragment();
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

    //    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
////        return super.instantiateItem(container, position);
//        Fragment fragment = (Fragment)super.instantiateItem(container, position);
//
//        return fragment;
//    }
}