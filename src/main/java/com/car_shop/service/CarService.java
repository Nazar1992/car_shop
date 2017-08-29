package com.car_shop.service;

import com.car_shop.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService
{
    void add(Car car, MultipartFile multipartFile) throws Exception;
    void delete(int id);
    void update(Car car, MultipartFile multipartFile);
    Car getOne(int id);
    List<Car> getAll();
    Page<Car> findAllPages(Pageable pageable);
    void update(Car car);
}