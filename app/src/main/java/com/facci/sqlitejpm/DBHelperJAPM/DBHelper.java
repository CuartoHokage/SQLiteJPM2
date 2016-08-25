package com.facci.sqlitejpm.DBHelperJAPM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaime Paz Mero on 25/8/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NOMBRE= "votantes";
    public static final String TABLA_NOMBRE= "votantes_japm";
    public static final String COL_1= "ID_japm";
    public static final String COL_2= "Nombre_japm";
    public static final String COL_3= "Apellido_japm";
    public static final String COL_4= "RecintoElectoral_japm";
    public static final String COL_5= "AnoNacimiento_japm";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create tabla %s(ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s INTEGER)", TABLA_NOMBRE, COL_2, COL_3, COL_4, COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(db);
    }

    public  boolean Insertar(String nombre,String apellido,String recinto, int nacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,nacimiento);
        long resultado=db.insert(TABLA_NOMBRE,null,contentValues );;

        if (resultado == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor consultarDatos () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor consul = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return consul;
    }

    public boolean modificarRegistro (String Id,String Nombre,String Apellido,String RecintoElectoral,int AnoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Nombre);
        contentValues.put(COL_3,Apellido);
        contentValues.put(COL_4,RecintoElectoral);
        contentValues.put(COL_5,AnoNacimiento);
        db.update(TABLA_NOMBRE,contentValues,"Id = ?",new String[]{Id});
        return true;
    }
    public Integer eliminar (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"Id = ?",new String[]{Id});
    }
}
