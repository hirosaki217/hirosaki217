package com.example.gridview;

public class DoGiaDung {
    private String name;
    private Integer image;
    private String title;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DoGiaDung(String name, Integer image, String title, double price) {
        this.name = name;
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public DoGiaDung() {
    }

    @Override
    public String toString() {
        return "DoGiaDung{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
