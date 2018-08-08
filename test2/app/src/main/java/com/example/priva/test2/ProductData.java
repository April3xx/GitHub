package com.example.priva.test2;

/**
 * Created by priva on 20-Nov-17.
 */

public class ProductData {
    private int id;
    private String product;
    private String detail;
    private int price;
    private  int number;

    public ProductData(int id, String product, String detail, int price,int number) {
        this.id = id;
        this.product = product;
        this.detail = detail;
        this.price = price;
        this.number = number;
    }

    public int getId() {
        return this.id;
    }

    public String getProduct() {
        return this.product;
    }

    public String getDetail() {
        return this.detail;
    }

    public int getPrice() {
        return this.price;
    }
    public int getNumber(){
        return this.number;
    }
}