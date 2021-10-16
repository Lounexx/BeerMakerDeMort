package com.example.beermakerdemort.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String NOMDB = "Beermaker.sqlite";
    private static final Integer VERSION = 1;

    public MySQLiteOpenHelper(Context context) {
        super(context,NOMDB,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "create table IF NOT EXISTS Recette (id INTEGER PRIMARY KEY, volumeBiere REAL NOT NULL" +
                ", degreAlcool REAL NOT NULL, ebc REAL NOT NULL);";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
