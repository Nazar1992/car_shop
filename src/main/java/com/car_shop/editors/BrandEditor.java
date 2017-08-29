package com.car_shop.editors;

import com.car_shop.entity.Brand;

import java.beans.PropertyEditorSupport;

public class BrandEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String id) throws IllegalArgumentException
    {
        Brand brand = new Brand();
        brand.setId(Integer.parseInt(id));
        setValue(brand);
    }
}
