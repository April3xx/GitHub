package com.example.priva.test2;

/**
 * Created by priva on 20-Nov-17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ShopDB4";
    private static final int DB_VERSION = 1;

    private static final String DB_CREATE =
            "" + "CREATE TABLE shoplist "
                    +
            "(" + "id INTEGER PRIMARY KEY, "
            + "product TEXT NOT NULL, "
            + "detail TEXT NOT NULL, "
            +
                    "number INTEGER NOT NULL,"
            +
            "price INTEGER NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(), "Upgread database version from version" + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS shoplist");
        onCreate(db);
    }
}
