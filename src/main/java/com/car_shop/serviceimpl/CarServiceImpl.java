package com.car_shop.serviceimpl;

import com.car_shop.dao.BrandDao;
import com.car_shop.dao.CarDao;
import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;
import com.car_shop.service.CarService;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;
    private final BrandDao brandDao;

    private final Validator validator;

    @Autowired
    public CarServiceImpl(CarDao carDao, BrandDao brandDao, @Qualifier("carValidator") Validator validator) {
        this.carDao = carDao;
        this.brandDao = brandDao;
        this.validator = validator;
    }

    public void add(Car car, MultipartFile multipartFile) throws Exception {
        validator.validate( car );
        String path = "C:" + File.separator + "apache-tomcat-7.0.78" + "/" + "resources" + "/"
                + "car_shop" + "/" + "img" + "/" + "cars" + "/" + multipartFile.getOriginalFilename();

        car.setPathImage( "img" + "/" + "cars" + "/" + multipartFile.getOriginalFilename() );

        File file = new File( path );

        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo( file );
        } catch (IOException e) {
            System.out.println( "error with file" );
        }
        carDao.save( car );
    }

    public void delete(int id) {
        carDao.delete( id );
    }

    public void update(Car car, MultipartFile multipartFile) {
        if (multipartFile != null) {
            String path = "C:" + "/" + "apache-tomcat-7.0.78" + "/" + "resources" + "/"
                    + "car_shop" + "/" + "img" + "/" + "cars" + "/" + multipartFile.getOriginalFilename();

            car.setPathImage( "img" + "/" + "cars" + "/" + multipartFile.getOriginalFilename() );

            File file = new File( path );

            try {
                file.mkdirs();
                multipartFile.transferTo( file );
            } catch (IOException e) {
                System.out.println( "error with file" );
            }

        }
        if (car.getBrand() != null && car.getId() != 0 && car.getBrand().getName() == null) {
            Brand brand = brandDao.findOne(car.getBrand().getId());
            car.setBrand(brand);
        }
        carDao.save(car);
    }

    public Car getOne(int id) {
        return carDao.findOne( id );
    }

    public List<Car> getAll() {
        return carDao.findAll();
    }

    @Override
    public Page<Car> findAllPages(Pageable pageable) {
        return carDao.findAll( pageable );
    }

    @Override
    public void update(Car car) {
        carDao.save( car );
    }
}
