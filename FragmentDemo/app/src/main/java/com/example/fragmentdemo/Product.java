package com.example.fragmentdemo;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String detail;
    private String price;
    private Integer image;

    public Product(int id, String name, String detail, String price, Integer image) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }


    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", price='" + price + '\'' +
                ", image=" + image +
                '}';
    }
}
