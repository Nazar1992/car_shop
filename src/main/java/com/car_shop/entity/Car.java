package com.car_shop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "car")
public class Car extends AbstractEntity
{
    @Column(name= "name")
    private String name;

    @Column(name= "content")
    private String content;

    @Column(name= "price")
    private int price;

    private int quantity;

    @Column(name = "image")
    private String pathImage;

    @ManyToOne
    private Brand brand;

    @ManyToMany
    @JoinTable(name = "users_cars",
            joinColumns = @JoinColumn(name = "carId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "item_billable",
            joinColumns = @JoinColumn(name = "carId"),
            inverseJoinColumns = @JoinColumn(name = "billableId"))

    private Set<Billable> billable = new HashSet<>();

    public Car() {}

    public Car(String name, String content, int price, Brand brand) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.quantity = 1;
        this.brand = brand;
    }

    public Car(String name, String content, int price, String pathImage, Brand brand) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.pathImage = pathImage;
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Content: " + content + ", Price: " + price
                + ", Brand: " + brand;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Billable> getBillable() {
        return billable;
    }

    public void setBillable(Set<Billable> billable) {
        this.billable = billable;
    }
}