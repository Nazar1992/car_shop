package com.car_shop.serviceimpl;

import com.car_shop.dao.BillableDao;
import com.car_shop.dao.UserDao;
import com.car_shop.entity.Billable;
import com.car_shop.entity.Role;
import com.car_shop.entity.User;
import com.car_shop.service.UserService;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService
{
    private final UserDao userDao;

    private final BillableDao billableDao;

    private final Validator validator;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BillableDao billableDao, @Qualifier("userValidator") Validator validator, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.billableDao = billableDao;
        this.validator = validator;
        this.encoder = encoder;
    }

    public void add(User user) throws Exception
    {
        validator.validate(user);
        user.setRole(Role.ROLE_USER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setBlock(true);
        userDao.save(user);
    }

    public void delete(int id)
    {
        User user = userDao.userWithBillables(id);
        for (Billable billable : user.getBillable())
        {
            billable.setUser(null);
            billableDao.saveAndFlush( billable );
        }
        userDao.delete(id);
    }

    public void update(User user)
    {
        userDao.save(user);
    }

    public User getOne(int id)
    {
        return userDao.findOne(id);
    }

    public List<User> getAll()
    {
        return userDao.findAll();
    }

    @Override
    public User userWithBillables(int id)
    {
        return userDao.userWithBillables(id);
    }

    @Override
    public User userWithItems(int id)
    {
        return userDao.userWithItems(id);
    }

    @Override
    public User findByUuid(String uuid)
    {
        return userDao.findByUuId(uuid);
    }

    @Override
    public Page<User> findAllPages(Pageable pageable)
    {
        return userDao.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
    {
        return userDao.findByNameOrEmail(name);
    }
}