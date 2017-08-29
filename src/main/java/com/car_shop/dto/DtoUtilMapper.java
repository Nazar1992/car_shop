package com.car_shop.dto;

import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class DtoUtilMapper {

    public static CarDto carToCarDto(Car car) {
        if (car.getBrand() != null)
            return new CarDto( car.getId(), car.getName(), car.getContent(), car.getPrice(), car.getPathImage(), brandWithoutCars( car.getBrand() ) );
        else
            return new CarDto( car.getId(), car.getName(), car.getContent(), car.getPrice(), car.getPathImage(), null);
    }

    public static Brand brandWithoutCars(Brand brand) {
        return new Brand( brand.getName() );
    }


    public static List<BrandDto> brandsToBrandsDtos(List<Brand> brands) {
        List<BrandDto> brandDtos = new ArrayList<>();

        for (Brand brand : brands) {
            brandDtos.add( new BrandDto( brand.getId(), brand.getName() ) );
        }
        return brandDtos;
    }

    public static List<CarDto> carsToCarsDtos(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars) {
            CarDto carDto = carToCarDto( car );
            carDtos.add( carDto );
        }
        return carDtos;
    }
}
