package com.car_shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "brand")
public class Brand extends AbstractEntity
{
    @Column(name= "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Car> car = new HashSet<>();

    public Brand() {}

    public Brand(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}