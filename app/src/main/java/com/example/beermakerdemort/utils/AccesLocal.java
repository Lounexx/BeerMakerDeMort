package com.example.beermakerdemort.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beermakerdemort.Objects.Recette;

import java.util.ArrayList;

public class AccesLocal {

    private MySQLiteOpenHelper accesDB;
    private SQLiteDatabase database;

    public AccesLocal(Context context){
        accesDB = new MySQLiteOpenHelper(context);
    }

    public void initDB(){
        database = accesDB.getWritableDatabase();
        accesDB.onCreate(database);
    }

    public void saveRecette(Recette recette){
        database = accesDB.getWritableDatabase();
        String sql = "INSERT INTO Recette(volumeBiere, degreAlcool, ebc) VALUES("+recette.getVolumeBiere()+
                ","+recette.getDegreAlcool()+","+recette.getEbc()+");";
        database.execSQL(sql);
        database.close();
    }
    public void deleteTable(){
        database = accesDB.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS Recette");
        database.close();
    }

    public ArrayList<Recette> getAllRecettes(){
        ArrayList<Recette> recettes = new ArrayList<>();
        database = accesDB.getWritableDatabase();
        String request = "SELECT * FROM Recette";
        Cursor cursor = database.rawQuery(request,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer id = cursor.getInt(0);
            double volumeBiere = cursor.getDouble(1);
            double degreAlcool = cursor.getDouble(2);
            double ebc = cursor.getDouble(3);
            recettes.add(new Recette(id,volumeBiere,degreAlcool,ebc));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return recettes;
    }

}
