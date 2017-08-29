package com.car_shop.validator.brand;

import com.car_shop.dao.BrandDao;
import com.car_shop.entity.Brand;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandValidator implements Validator {

    private final BrandDao brandDao;

    @Autowired
    public BrandValidator(BrandDao brandDao)
    {
        this.brandDao = brandDao;
    }

    @Override
    public void validate(Object o) throws Exception
    {
        Brand brand = (Brand) o;

        if(brand.getName().isEmpty())
        {
            throw new BrandException(BrandValidationMessages.EMPTY_BRAND_NAME_FIELD);
        }

        else if(brandDao.findByName(brand.getName()) != null)
        {
            throw new BrandException(BrandValidationMessages.BRAND_NAME_ALREADY_EXIST);
        }
    }
}
