package com.cibertec.delicias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.cibertec.delicias.conexion.ConexionSQLite;
import com.cibertec.delicias.models.Producto;
import com.cibertec.delicias.utilities.Utilities;

import java.util.ArrayList;

public class ProductoDAO {
    ConexionSQLite con;
    SQLiteDatabase db;
    ArrayList<Producto> listProd = new ArrayList<>();
    Context context;
    ContentValues content;

    public ProductoDAO(Context context) {
        this.context = context;
        this.con = new ConexionSQLite(context, Utilities.BD, null, 1);
    }

    public boolean insertProd(Producto producto) {
        ContentValues content = createOrEdit(producto);
        db = con.getWritableDatabase();
        boolean result = (db.insert("producto", null, content)) > 0;
        db.close();
        return result;
    }

    public boolean editProd(Producto producto) {
        ContentValues content = createOrEdit(producto);
        db = con.getWritableDatabase();
        boolean result = (db.update("producto", content, "id = " + producto.getId(), null)) > 0;
        db.close();
        return result;
    }

    public ArrayList<Producto> listProd() {
        listProd.clear();
        db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from producto", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                listProd.add(new Producto(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return listProd;
    }

    public boolean findProd(int position) {
        db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from producto where id = ?", new String[]{String.valueOf(position)});
        boolean result =  (cursor != null && cursor.getCount() > 0);
        db.close();
        return result;
    }

    public boolean delete(int id) {
        db = con.getWritableDatabase();
        boolean result = (db.delete("producto", "id = " + id, null)) > 0;
        db.close();
        return result;
    }

    public ContentValues createOrEdit(@NonNull Producto producto) {
        content = new ContentValues();
        content.put("title", producto.getTitle());
        content.put("cost", producto.getCost());
        content.put("url", producto.getUrl());
        return content;
    }

}
