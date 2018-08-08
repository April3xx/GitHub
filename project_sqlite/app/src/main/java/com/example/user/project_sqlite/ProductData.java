package com.example.user.project_sqlite;

/**
 * Created by USER on 11/13/2017.
 */

public class ProductData {
    private int id;
    private String name;
    private String email;
    private int year;

    public ProductData(int id, String name, String email, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.year = year;
    }

    public int getId() {return this.id;}

    public String getProduct() {return this.name;}

    public String getDetail() {
        return this.email;
    }

    public int getPrice() {
        return this.year;
    }
}
