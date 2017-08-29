package com.car_shop.controller;

import com.car_shop.editors.BrandEditor;
import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;
import com.car_shop.service.BrandService;
import com.car_shop.service.CarService;
import com.car_shop.validator.car.CarValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class CarController
{
    private final CarService carService;

    private final BrandService brandService;

    @Autowired
    public CarController(CarService carService, BrandService brandService) {
        this.carService = carService;
        this.brandService = brandService;
    }

    @InitBinder
    public void init(WebDataBinder binder)
    {
        binder.registerCustomEditor(Brand.class, new BrandEditor());
    }

    @GetMapping("/car")
    public String addCar(Model model)
    {
        model.addAttribute("car", new Car());
        model.addAttribute("brands", brandService.getAll());
        return "views-admin-car";
    }

    @PostMapping("/saveCar")
    public String addCar(@ModelAttribute Car car, Model model, @RequestAttribute("image") MultipartFile image, @RequestParam(value = "_csrf", required = false) String csrf)
    {
        try
        {
            carService.add(car, image);
        }
        catch (Exception e)
        {
            if(e.getMessage().equals(CarValidationMessages.EMPTY_ITEMNAME_FIELD)
                    || e.getMessage().equals(CarValidationMessages.ITEMNAME_ALREADY_EXIST))
            {
                model.addAttribute("itemnameException", e.getMessage());
            }
            else if(e.getMessage().equals(CarValidationMessages.EMPTY_CONTENT_FIELD))
            {
                model.addAttribute("itemcontentException", e.getMessage());
            }
            else if(e.getMessage().equals(CarValidationMessages.ZERO_PRICE_FIELD))
            {
                model.addAttribute("itempriceException", e.getMessage());
            }
            model.addAttribute("brands", brandService.getAll());
            return "redirect:/listOfCars";
        }
        return "redirect:/listOfCars";
    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable int id)
    {
        carService.delete(id);
        return "redirect:/listOfCars";
    }

    @GetMapping("/updateCar/{id}")
    public String getItem(@PathVariable int id, Model model)
    {
        Car car = carService.getOne(id);
        List<Brand> brands = brandService.getAll();
        if (car.getBrand() == null) {
            brands.add(0, new Brand(""));
        } else {
            for (int i = 0; i < brands.size(); i++) {
                if (car.getBrand().getId() == brands.get(i).getId()) {
                    brands.remove(i);
                    brands.add(0, car.getBrand());
                    break;
                }
            }
        }
        model.addAttribute("car", car);
        model.addAttribute("brands", brands);
        return "views-admin-updateCar";
    }

    @PostMapping("/updateCar/{id}")
    public String updateCar(@ModelAttribute Car car, @PathVariable int id,
                              @RequestAttribute("image") MultipartFile image)
    {
        car.setId(id);
        if(image.isEmpty())
        {
            carService.update(car);
        }
        else
        {
            carService.update(car, image);
        }
        return "redirect:/listOfCars";
    }

    @GetMapping("/listOfCars")
    public String allCars(Model model, @PageableDefault Pageable pageable)
    {
        model.addAttribute("cars", carService.findAllPages(pageable));
        return "views-admin-listOfCars";
    }
}
