package com.haixiajiemei.app.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.haixiajiemei.app.Module.Order.Model.ECShopping;

public class ECShoppingDB {
    public static final String TABLE_NAME = "ECShopping";

    public static final String DATA = "Data";

    public SQLiteDatabase DB;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "( " +
                    DATA + " TEXT )";

    public static final String DropLogTable =
            "drop table if exists " + TABLE_NAME;

    public ECShoppingDB(Context context) {
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

    public static String GetECShoppingString(Context context) {
        ECShoppingDB db = new ECShoppingDB(context);
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
        ECShoppingDB DB = new ECShoppingDB(context);
        DB.Insert(data);
    }

    public static void UpdateData(Context context, String data) {
        ECShoppingDB DB = new ECShoppingDB(context);
        String originData = GetECShoppingString(context);

        DB.Update(originData, data);
    }

    public static void DelData(Context context, String data) {
        ECShoppingDB DB = new ECShoppingDB(context);
        DB.Deldate(data);
    }

    public static ECShopping GetECShopping(Context context) {
        ECShoppingDB DB = new ECShoppingDB(context);
        Cursor mCursor = DB.GetData();
        Gson gson = new Gson();
        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {
                String s = mCursor.getString(mCursor.getColumnIndex(DATA));
                ECShopping data = gson.fromJson(mCursor.getString(mCursor.getColumnIndex(DATA)), ECShopping.class);
                mCursor.close();
                return data;
            }
        }
        mCursor.close();
        return null;
    }
}
