package com.car_shop.controller;

import com.car_shop.dto.BrandDto;
import com.car_shop.dto.DtoUtilMapper;
import com.car_shop.entity.Brand;
import com.car_shop.service.BrandService;
import com.car_shop.validator.brand.BrandValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brand")
    public List<BrandDto> loadBrands() {
        return DtoUtilMapper.brandsToBrandsDtos( brandService.getAll() );
    }

    @PostMapping("/brand")
    public List<BrandDto> addBrand(@RequestBody Brand brand) {
        try {
            brandService.add( brand );
        } catch (Exception e) {
            if (e.getMessage().equals( BrandValidationMessages.EMPTY_BRAND_NAME_FIELD ) ||
                    e.getMessage().equals( BrandValidationMessages.BRAND_NAME_ALREADY_EXIST )) {

            }
        }
        return DtoUtilMapper.brandsToBrandsDtos( brandService.getAll() );
    }

    @DeleteMapping("/brand")
    public List<BrandDto> deleteBrand(@RequestBody String brandId) {
        brandService.delete( Integer.valueOf( brandId ) );
        return DtoUtilMapper.brandsToBrandsDtos( brandService.getAll() );
    }

    @PutMapping("/brand")
    public List<BrandDto> updateBrand(@RequestBody String brandInfo) {
        brandService.update( brandInfo );
        return DtoUtilMapper.brandsToBrandsDtos( brandService.getAll() );
    }
}
