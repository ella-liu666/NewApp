package com.haixiajiemei.app.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DataBaseName = "Shopping.db";

    private static SQLiteDatabase DB;

    private static int Version = 1;
    public SQLiteHelper(Context context)
    {
        super(context, DataBaseName, null, Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ShoppingCartDB.CREATE_TABLE);
        sqLiteDatabase.execSQL(ECShoppingDB.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ShoppingCartDB.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ECShoppingDB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public static SQLiteDatabase getDataBase(Context context)
    {
        if (DB == null || !DB.isOpen())
        {
            DB = new SQLiteHelper(context).getWritableDatabase();
        }
        return DB;
    }

}
