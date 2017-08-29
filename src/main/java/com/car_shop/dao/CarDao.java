package com.car_shop.dao;

import com.car_shop.entity.Billable;
import com.car_shop.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CarDao extends JpaRepository<Car, Integer>
{
    Car findByName(String name);
    @Query("select distinct i from Car i left join fetch i.billable where i.id =:id")
    Car getItemWithBillables(@Param("id") int id);

    @Query("select distinct i from Car i left join fetch i.billable")
    Set<Billable> getItemWithBillables();

    @Query("select i from Car i left join fetch i.users where i.id =:id")
    Car itemWithUsers(@Param("id") int id);

    @Query("select i from Car i where i.name like %:search%")
    List<Car> searchItems(@Param("search") String search);

    @Query("select i from Car i where i.price between :min and :max")
    List<Car> searchByPrice(@Param("min") int min, @Param("max") int max);
}