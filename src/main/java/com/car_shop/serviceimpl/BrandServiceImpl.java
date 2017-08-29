package com.car_shop.serviceimpl;

import com.car_shop.dao.BrandDao;
import com.car_shop.dao.CarDao;
import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;
import com.car_shop.service.BrandService;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandDao brandDao;

    private final CarDao carDao;

    private final Validator validator;

    @Autowired
    public BrandServiceImpl(BrandDao brandDao, CarDao carDao, @Qualifier("brandValidator") Validator validator) {
        this.brandDao = brandDao;
        this.carDao = carDao;
        this.validator = validator;
    }

    public void add(Brand brand) throws Exception {

        validator.validate( brand );

        brandDao.save( brand );
    }

    public void delete(int id) {
        Brand brand = brandDao.brandWithCars( id );

        for (Car car : brand.getCar()) {
            car.setBrand( null );
            carDao.saveAndFlush( car );
        }
        brandDao.delete( id );
    }

    public void update(String categoryInfo) {
        Brand brand = brandDao.findOne( Integer.parseInt( categoryInfo.split( "_" )[1] ) );
        brand.setName( categoryInfo.split( "_" )[0] );
        brandDao.save( brand );
    }

    public Brand getOne(int id) {
        return brandDao.findOne( id );
    }

    public List<Brand> getAll() {
        return brandDao.findAll();
    }
}