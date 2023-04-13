package com.cibertec.delicias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.cibertec.delicias.conexion.ConexionSQLite;
import com.cibertec.delicias.models.Usuario;
import com.cibertec.delicias.utilities.Utilities;

import java.util.ArrayList;

public class UsuarioDAO {
    ConexionSQLite con;
    SQLiteDatabase db;/*
    ArrayList<Usuario> listUser = new ArrayList<>();
    Usuario usuario;*/
    Context context;
    ContentValues content;

    public UsuarioDAO(@NonNull Context context) {
        this.context = context;
        this.con = new ConexionSQLite(context, Utilities.BD, null, 1);
    }

    public boolean insertUser(Usuario usuario) {
        ContentValues content = createOrEdit(usuario);
        db = con.getWritableDatabase();
        boolean result = (db.insert("usuario", null, content)) > 0;
        db.close();
        return result;
    }

    public boolean editUser(Usuario usuario) {
        ContentValues content = createOrEdit(usuario);
        db = con.getWritableDatabase();
        boolean result = (db.update("usuario", content, "id = " + usuario.getId(), null)) > 0;
        db.close();
        return result;
    }
    /*
    public ArrayList<Usuario> listUser() {
        listUser.clear();
        db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                listUser.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());
        }

        return listUser;
    }
    */
    public boolean findUser(String mail, String pass) {
        db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where mail = ? and pass = ?", new String[]{mail, pass});
        boolean result = (cursor != null && cursor.getCount() > 0);
        db.close();
        return result;
    }
    /*
    public boolean delete(int id) {
        db = con.getWritableDatabase();
        return (db.delete("usuario", "id = " + id, null)) > 0;
    }
    */
    public ContentValues createOrEdit(@NonNull Usuario usuario) {
        content = new ContentValues();
        content.put("mail", usuario.getMail());
        content.put("name", usuario.getName());
        content.put("user", usuario.getUser());
        content.put("pass", usuario.getPass());
        return content;
    }
}