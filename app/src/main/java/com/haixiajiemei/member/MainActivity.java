package com.haixiajiemei.member;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.haixiajiemei.member.Module.Account.Fragment.LoginFragment;
import com.haixiajiemei.member.Module.Home.Fragment.HomeFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.haixiajiemei.member.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottom_navigation_view;

    private HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        switchFragmentToActivity(R.id.fragment_container,homeFragment, this);

        bottom_navigation_view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragmentToActivity(R.id.fragment_container,homeFragment, this);
                    return true;
                case R.id.navigation_order:
//                    OrderFragment orderFragment = new OrderFragment();
//                    switchFragmentToActivity(R.id.fragment_container,orderFragment,this);
                    switchFragmentToActivity(R.id.fragment_container,homeFragment, this);
                    CreateAlertDialogTool(this);
                    return true;
                case R.id.navigation_organic:
                    switchFragmentToActivity(R.id.fragment_container,homeFragment, this);
                    CreateAlertDialogTool(this);
                    return true;
                case R.id.navigation_shopping:
                    switchFragmentToActivity(R.id.fragment_container,homeFragment, this);
                    CreateAlertDialogTool(this);
                    return true;
                case R.id.navigation_setting:
                    if(!"".equals(getSharedPreferences("UserToken", MODE_PRIVATE).getString("refresh_token",""))){
                        SettingFragment settingFragment = new SettingFragment();
                        switchFragmentToActivity(R.id.fragment_container,settingFragment, this);
                    }else {
                        LoginFragment loginFragment = new LoginFragment();
                        switchFragmentToActivity(R.id.fragment_container,loginFragment, this);
                    }
                    return true;
            }
            return false;
        });
    }

}
