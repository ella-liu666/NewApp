package com.haixiajiemei.app.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCartList;

import java.util.List;


public class ShoppingCartDB {
    public static final String TABLE_NAME = "ShoppingCart";

    public static final String DATA = "Data";

    public SQLiteDatabase DB;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "( " +
                    DATA + " TEXT )";

    public static final String DropLogTable =
            "drop table if exists " + TABLE_NAME;

    public ShoppingCartDB(Context context) {
        DB = SQLiteHelper.getDataBase(context);
    }


    public Cursor GetData() {
        return DB.rawQuery("select * from " + TABLE_NAME, null);
    }

    public void Insert(String data) {
        ContentValues cv = new ContentValues();
        cv.put("Data", data);
        DB.insert(TABLE_NAME, null, cv);
    }

    public void Update(String originData, String data) {
        ContentValues cv = new ContentValues();
        cv.put(DATA, data);
        DB.update(TABLE_NAME, cv, DATA + "= '" + originData + "'", null);
    }

    public void Deldate(String data) {
        ContentValues cv = new ContentValues();
        cv.put(DATA, data);
        DB.delete(TABLE_NAME, null, null);
    }

    public static String GetShoppingCartString(Context context) {
        ShoppingCartDB db = new ShoppingCartDB(context);
        Cursor mCursor = db.GetData();
//        SQLiteHelper.fix();
        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {
                String staticData = mCursor.getString(mCursor.getColumnIndex(DATA));
                mCursor.close();
                return staticData;
            }
        }
        mCursor.close();
        return null;
    }

    public static void InsertData(Context context, String data) {
        ShoppingCartDB DB = new ShoppingCartDB(context);
        DB.Insert(data);
    }

    public static void UpdateData(Context context, String data) {
        ShoppingCartDB DB = new ShoppingCartDB(context);
        String originData = GetShoppingCartString(context);

        DB.Update(originData, data);
    }

    public static void DelData(Context context, String data) {
        ShoppingCartDB DB = new ShoppingCartDB(context);
        DB.Deldate(data);
    }

    public static ShoppingCartList GetShoppingCart(Context context) {
        ShoppingCartDB DB = new ShoppingCartDB(context);
        Cursor mCursor = DB.GetData();
        Gson gson = new Gson();
        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {
                String s = mCursor.getString(mCursor.getColumnIndex(DATA));
                ShoppingCartList data = gson.fromJson(mCursor.getString(mCursor.getColumnIndex(DATA)), ShoppingCartList.class);
                mCursor.close();
                return data;
            }
        }
        mCursor.close();
        return null;
    }
}
