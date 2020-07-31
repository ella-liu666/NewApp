package com.haixiajiemei.app;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.haixiajiemei.app.JPush.ExampleUtil;
import com.haixiajiemei.app.Module.Account.Fragment.LoginFragment;
import com.haixiajiemei.app.Module.Home.Fragment.HomeFragment;
import com.haixiajiemei.app.Module.Order.Fragment.OrderFragment;
import com.haixiajiemei.app.Module.Order.Fragment.ShoppingCartFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.haixiajiemei.app.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.app.Util.Proclaim.CARTSUCCESS;
import static com.haixiajiemei.app.Util.Proclaim.SHOPPINGCART;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottom_navigation_view;

    private HomeFragment homeFragment = new HomeFragment();
    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String regist = JPushInterface.getRegistrationID(this);
        Log.e("regist_person", regist);
        registerMessageReceiver();

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            switch (intent.getExtras().getString("Type")) {
                case SHOPPINGCART:
                    LoginFragment loginFragment = new LoginFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Type", intent.getExtras().getString("Type"));
                    loginFragment.setArguments(bundle);
                    switchFragmentToActivity(R.id.fragment_container, loginFragment, this);
                    break;
                case CARTSUCCESS:
                    SettingFragment settingFragment = new SettingFragment();
                    switchFragmentToActivity(R.id.fragment_container, settingFragment, this);
                    break;
            }
        } else {

            switchFragmentToActivity(R.id.fragment_container, homeFragment, this);
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragmentToActivity(R.id.fragment_container, homeFragment, this);
                    return true;
                case R.id.navigation_order:
                    OrderFragment orderFragment = new OrderFragment();
                    switchFragmentToActivity(R.id.fragment_container, orderFragment, this);
                    return true;
//                case R.id.navigation_organic:
//                    switchFragmentToActivity(R.id.fragment_container,homeFragment, this);
//                    CreateAlertDialogTool(this);
//                    return true;
                case R.id.navigation_shopping:
                    ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
                    switchFragmentToActivity(R.id.fragment_container, shoppingCartFragment, this);
                    return true;
                case R.id.navigation_setting:
                    if (getSharedPreferences("UserToken", MODE_PRIVATE).getBoolean("loginStatus", true)) {
                        SettingFragment settingFragment = new SettingFragment();
                        switchFragmentToActivity(R.id.fragment_container, settingFragment, this);
                    } else {
                        LoginFragment loginFragment = new LoginFragment();
                        switchFragmentToActivity(R.id.fragment_container, loginFragment, this);
                    }
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE+" : "+messge+"\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS+" : "+extras+"\n");
                }
//                Constant.showToast(MainActivity.this, showMsg.toString());
            }
        }
    }
}

