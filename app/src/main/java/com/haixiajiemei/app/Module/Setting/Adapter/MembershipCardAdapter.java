package com.haixiajiemei.app.Module.Setting.Adapter;

import com.haixiajiemei.app.Module.Setting.Fragment.BuyMembershipCardFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.MyMembershipCardFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MembershipCardAdapter extends FragmentPagerAdapter {

    private String[] item;

    public MembershipCardAdapter(FragmentManager supportFragmentManager, String[] item) {
        super(supportFragmentManager);
        this.item = item;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BuyMembershipCardFragment();
            case 1:
                return new MyMembershipCardFragment();
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
