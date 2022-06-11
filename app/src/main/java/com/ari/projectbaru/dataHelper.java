package com.ari.projectbaru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pengguna.db";
    private static final int DATABASE_VERSION = 1;
    public dataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate (SQLiteDatabase db) {
        String sql = "create table biodataUser (no integer primary key, nama text null,nohp text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodataUser (no, nama, nohp, jk, alamat) VALUES ('1', 'ARI SUSANTO', '20416255201007', 'L', 'Karawang');";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // TODO Auto-generated method stub
        String query;
        query = "DROP TABLE IF EXISTS biodataUser";
        db.execSQL(query);
        onCreate(db);
    }
}