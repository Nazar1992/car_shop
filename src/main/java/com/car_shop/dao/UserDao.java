package com.car_shop.dao;

import com.car_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer>
{
    User findByName(String name);
    User findByEmail(String email);
    @Query("select u from User u left join fetch u.billable where u.id=:id")
    User userWithBillables(@Param("id") int id);

    @Query("select u from User u left join fetch u.cars where u.id=:id")
    User userWithItems(@Param("id") int id);

    @Query("select u from User u where u.uuId =:uuId")
    User findByUuId(@Param("uuId") String uuId);

    @Query("select u from User u where u.name=:value or u.email=:value")
    User findByNameOrEmail(@Param("value") String param);
}