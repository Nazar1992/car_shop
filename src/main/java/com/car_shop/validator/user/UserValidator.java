package com.car_shop.validator.user;

import com.car_shop.dao.UserDao;
import com.car_shop.entity.User;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator
{
    private final UserDao userDao;

    @Autowired
    public UserValidator(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public void validate(Object o) throws Exception
    {
        User user = (User) o;

        if(user.getName().isEmpty())
        {
            throw new UserException(UserValidationMessages.EMPTY_USERNAME_FIELD);
        }

        else if(userDao.findByName(user.getName()) != null)
        {
            throw new UserException(UserValidationMessages.USERNAME_ALREADY_EXIST);
        }

        else if(user.getEmail().isEmpty())
        {
            throw new UserException(UserValidationMessages.EMPTY_EMAIL_FIELD);
        }

        else if(!user.getEmail().contains("@"))
        {
            throw new UserException(UserValidationMessages.WRONG_EMAIL);
        }

        else if(userDao.findByEmail(user.getEmail()) != null)
        {
            throw new UserException(UserValidationMessages.EMAIL_ALREADY_EXIST);
        }

        else if(user.getPassword().isEmpty())
        {
            throw new UserException(UserValidationMessages.EMPTY_PASSWORD_FIELD);
        }

        else if(user.getPassword().length() < 4)
        {
            throw new UserException(UserValidationMessages.TOO_SHORT_PASSWORD);
        }

        else if(user.getAddress().isEmpty())
        {
            throw new UserException(UserValidationMessages.EMPTY_ADDRESS_FIELD);
        }
    }
}