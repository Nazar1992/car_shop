package com.car_shop.editors;

import com.car_shop.entity.Car;

import java.beans.PropertyEditorSupport;

public class CarEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String id) throws IllegalArgumentException
    {
        Car car = new Car();
        car.setId(Integer.parseInt(id));
        setValue(car);
    }
}
