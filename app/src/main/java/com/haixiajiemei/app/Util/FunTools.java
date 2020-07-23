package com.haixiajiemei.app.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;
import com.haixiajiemei.app.Module.Setting.Model.Expenses;
import com.haixiajiemei.app.Module.Setting.Model.Recharge;
import com.haixiajiemei.app.Parser.ClassParser;
import com.haixiajiemei.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FunTools {

    public static void switchFragmentToActivity(int id,Fragment var2, FragmentActivity activity){
        FragmentManager fragmentManager= activity.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(id, var2);
        transaction.commit();
    }

    public static void switchFragmentToBack(int id,Fragment var2, FragmentActivity activity){
        FragmentManager fragmentManager= activity.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(id, var2);
        //回退
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static List<ImgAndTxt> JSONArrayToTags(String JSONArray) {
        String JO = null;
        List<ImgAndTxt> test=new ArrayList<>();
        try {
            org.json.JSONArray array = new JSONArray(JSONArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JO = jsonObject.toString();
                test.add(ClassParser.toData(JO, ImgAndTxt.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static List<Recharge> JSONArrayToRecharge(String JSONArray) {
        String JO = null;
        List<Recharge> test=new ArrayList<>();
        try {
            org.json.JSONArray array = new JSONArray(JSONArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JO = jsonObject.toString();
                test.add(ClassParser.toData(JO, Recharge.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static List<Expenses> JSONArrayToExpenses(String JSONArray) {
        String JO = null;
        List<Expenses> test=new ArrayList<>();
        try {
            org.json.JSONArray array = new JSONArray(JSONArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JO = jsonObject.toString();
                test.add(ClassParser.toData(JO, Expenses.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static List<Coupon> JSONArrayToCoupon(String JSONArray) {
        String JO = null;
        List<Coupon> test=new ArrayList<>();
        try {
            org.json.JSONArray array = new JSONArray(JSONArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JO = jsonObject.toString();
                test.add(ClassParser.toData(JO, Coupon.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    public static void CreateAlertDialogTool(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.UnderConstruction);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void CreateAlertDialogTool(Context context, int Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void CreateAlertDialogTool(Context context, int Title, int Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void TwoBtnAlertDialogTool(Context context, int Title, int Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void FragmentKEYCODE_BACK(Context context,Fragment fragment, Activity activity, int Title, int Message){
        fragment.getView().setFocusableInTouchMode(true);
        fragment.getView().requestFocus();
        fragment.getView().setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                //攔截到的返回事件
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(Title);
                builder.setMessage(Message);
                builder.setPositiveButton(R.string.confirm, (dialog, which) ->  activity.onBackPressed());
                builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                return true;
            }
            return false;
        });
    }
}
