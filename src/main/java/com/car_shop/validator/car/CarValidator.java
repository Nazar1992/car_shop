package com.car_shop.validator.car;

import com.car_shop.dao.CarDao;
import com.car_shop.entity.Car;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator implements Validator
{
    private final CarDao carDao;

    @Autowired
    public CarValidator(CarDao carDao)
    {
        this.carDao = carDao;
    }

    @Override
    public void validate(Object o) throws Exception
    {
        Car car = (Car) o;

        if(car.getName().isEmpty())
        {
            throw new CarException(CarValidationMessages.EMPTY_ITEMNAME_FIELD);
        }

        else if(carDao.findByName(car.getName()) != null)
        {
            throw new CarException(CarValidationMessages.ITEMNAME_ALREADY_EXIST);
        }

        else if(car.getContent().isEmpty())
        {
            throw new CarException(CarValidationMessages.EMPTY_CONTENT_FIELD);
        }

        else if(car.getPrice() == 0)
        {
            throw new CarException(CarValidationMessages.ZERO_PRICE_FIELD);
        }
    }
}