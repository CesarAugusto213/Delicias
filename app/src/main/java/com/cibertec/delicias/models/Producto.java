package com.cibertec.delicias.models;

public class Producto {

    private int id;
    private String url;
    private String title;
    private String cost;

    public Producto(String url, String title, String cost) {
        this.url = url;
        this.title = title;
        this.cost = cost;
    }

    public Producto(int id, String url, String title, String cost) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
