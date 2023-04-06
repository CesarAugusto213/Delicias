package com.cibertec.delicias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.cibertec.delicias.models.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {
    SQLiteDatabase db;
    ArrayList<Usuario> listUser = new ArrayList<>();
    Usuario usuario;
    Context context;
    ContentValues content;

    final String BD = "BDDelicias";
    final String TB_USUARIO = "create table if not exists usuario(" +
            "id integer primary key autoincrement," +
            "mail text," +
            "name text," +
            "user text," +
            "pass text)";

    public UsuarioDAO(@NonNull Context context) {
        this.context = context;
        this.db = context.openOrCreateDatabase(BD, context.MODE_PRIVATE, null);
        db.execSQL(TB_USUARIO);
    }

    public boolean insertUser(Usuario usuario) {
        ContentValues content = createOrEdit(usuario);
        return (db.insert("usuario", null, content)) > 0;
    }

    public boolean editUser(Usuario usuario) {
        ContentValues content = createOrEdit(usuario);
        return (db.update("usuario", content, "id = " + usuario.getId(), null)) > 0;
    }

    public ArrayList<Usuario> listUser() {
        listUser.clear();
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

    public Usuario findUser(int position) {
        Cursor cursor = db.rawQuery("select * from usuario", null);
        cursor.moveToPosition(position);
        usuario = new Usuario(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));

        return usuario;
    }

    public boolean delete(int id) {
        return (db.delete("usuario", "id = " + id, null)) > 0;
    }

    public ContentValues createOrEdit(@NonNull Usuario usuario) {
        content = new ContentValues();
        content.put("mail", usuario.getMail());
        content.put("name", usuario.getName());
        content.put("user", usuario.getUser());
        content.put("pass", usuario.getPass());
        return content;
    }
}
