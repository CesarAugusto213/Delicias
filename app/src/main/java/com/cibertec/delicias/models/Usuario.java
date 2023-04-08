package com.cibertec.delicias.models;

public class Usuario {

    private int id;
    private String mail;
    private String name;
    private String user;
    private String pass;

    public Usuario(String mail, String name, String user, String pass) {
        this.mail = mail;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public Usuario(int id, String mail, String name, String user, String pass) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
