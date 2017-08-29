package com.car_shop.dto;

import com.car_shop.entity.Brand;

public class CarDto
{
    private int id;
    private String name;
    private String content;
    private int price;
    private String pathImage;
    private Brand brand;

    CarDto() {
    }

    public CarDto(int id, String name, String content, int price, String pathImage, Brand brand) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.price = price;
        this.pathImage = pathImage;
        this.brand = brand;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPathImage() {
        return pathImage;
    }

    void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", pathImage='" + pathImage + '\'' +
                ", category=" + brand +
                '}';
    }
}
