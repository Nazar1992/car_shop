package com.car_shop.service;

import com.car_shop.entity.Billable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface BillableService
{
    void add(Billable billable);
    void delete(int id);
    void update(Billable billable);
    Billable getOne(int id);
    List<Billable> getAll();
    Set<Billable> getBillableWithItems();
    void addToCart(Principal principal, int id);
    void deleteFromCart(int userId, int itemId);
    void buy(int userId);
    void getTotalPrice(int id);
    void makeSleep();
    Page<Billable> findAllPages(Pageable pageable);
}