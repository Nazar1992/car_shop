package com.car_shop.dao;

import com.car_shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrandDao extends JpaRepository<Brand, Integer>
{
    @Query("select c from Brand c left join fetch c.car where c.id=:id")
    Brand brandWithCars(@Param("id") int id);

    Brand findByName(String name);
}
