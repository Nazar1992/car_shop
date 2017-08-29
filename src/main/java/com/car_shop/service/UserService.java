package com.car_shop.service;

import com.car_shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService
{
    void add(User user) throws Exception;
    void delete(int id);
    void update(User user);
    User getOne(int id);
    List<User> getAll();
    User userWithBillables(int id);
    User userWithItems(int id);
    User findByUuid(String uuid);
    Page<User> findAllPages(Pageable pageable);
}