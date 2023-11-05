package com.example.xansomniapruebita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoGuitar {
    SQLiteDatabase bd;
    ArrayList<Guitar> lista = new ArrayList<Guitar>();
    Guitar guitar;
    Context context;
    String dbName = "BDGuitars";
    String table = "create table if not exists guitar(id integer primary key autoincrement, guitarModel text, guitarBrand text, guitarType text, guitarPrice text, storeLocation text)";

    public daoGuitar(Context context) {
        this.context = context;
        bd = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
        bd.execSQL(table);
    }

    public boolean insertar(Guitar guitar) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("guitarModel", guitar.getGuitarModel());
        contenedor.put("guitarBrand", guitar.getGuitarBrand());
        contenedor.put("guitarType", guitar.getGuitarType());
        contenedor.put("guitarPrice", guitar.getGuitarPrice());
        contenedor.put("storeLocation", guitar.getStoreLocation());
        return (bd.insert("guitar", null, contenedor)) > 0;
    }

    public boolean eliminar(int id) {
        return (bd.delete("guitar", "id=" + id, null)) > 0;
    }

    public boolean editar(Guitar guitar) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("guitarModel", guitar.getGuitarModel());
        contenedor.put("guitarBrand", guitar.getGuitarBrand());
        contenedor.put("guitarType", guitar.getGuitarType());
        contenedor.put("guitarPrice", guitar.getGuitarPrice());
        contenedor.put("storeLocation", guitar.getStoreLocation());
        return (bd.update("guitar", contenedor, "id=" + guitar.getId(), null)) > 0;
    }

    public ArrayList<Guitar> verTodo() {
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from guitar", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Guitar(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public Guitar verUno(int posicion) {
        Cursor cursor = bd.rawQuery("select * from guitar", null);
        cursor.moveToPosition(posicion);
        guitar = new Guitar(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5));
        return guitar;
    }
}
