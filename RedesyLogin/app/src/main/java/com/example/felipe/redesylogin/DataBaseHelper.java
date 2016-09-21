package com.example.felipe.redesylogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe on 17-09-2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    String sqlCreate  = "CREATE TABLE BASE (usuario TEXT, contrasena TEXT)";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        //db.execSQL("INSERT INTO BASE (usuario,contrasena) VALUES('Felipe','pass') ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP TABLE IF EXIST Usuario");
        //db.execSQL(sqlCreate);
    }
}
