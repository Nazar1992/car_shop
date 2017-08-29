package com.car_shop.controller;

import com.car_shop.dto.DtoUtilMapper;
import com.car_shop.dto.CarDto;
import com.car_shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCarController
{
    private final CarService carService;

    @Autowired
    public RestCarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/carSearch")
    public List<CarDto> loadCars()
    {
        return DtoUtilMapper.carsToCarsDtos(carService.getAll());
    }
}
