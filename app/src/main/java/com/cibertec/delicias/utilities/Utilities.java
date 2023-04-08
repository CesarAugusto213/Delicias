package com.cibertec.delicias.utilities;

public class Utilities {

    public static final String BD = "BDDelicias";

    //Usuario
    public static final String TB_USUARIO = "create table if not exists usuario(" +
            "id integer primary key autoincrement," +
            "mail text," +
            "name text," +
            "user text," +
            "pass text)";

}
