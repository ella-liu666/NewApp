package com.haixiajiemei.app.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DataBaseName = "ShoppingCart.db";

    private static SQLiteDatabase DB;

    private static int Version = 1;
    public SQLiteHelper(Context context)
    {
        super(context, DataBaseName, null, Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
