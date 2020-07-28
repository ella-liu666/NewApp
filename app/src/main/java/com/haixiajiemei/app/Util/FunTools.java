package com.haixiajiemei.app.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.KeyEvent;

import com.haixiajiemei.app.Parser.ClassParser;
import com.haixiajiemei.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


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


    public static List JSONArrayToClass(String JSONArray,Class t) {
        String JO = null;
        List test=new ArrayList<>();
        try {
            org.json.JSONArray array = new JSONArray(JSONArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JO = jsonObject.toString();
                test.add(ClassParser.toData(JO, t));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
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

    public static void CreateAlertDialogTool(Context context, String Title, int Message) {
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
