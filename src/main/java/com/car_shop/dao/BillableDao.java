package com.car_shop.dao;

import com.car_shop.entity.Billable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BillableDao extends JpaRepository<Billable, Integer>
{
    @Query("select distinct b from Billable b left join fetch b.car")
    Set<Billable> getBillableWithItems();
}
