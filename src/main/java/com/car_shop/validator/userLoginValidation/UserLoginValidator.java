package com.car_shop.validator.userLoginValidation;

import com.car_shop.dao.UserDao;
import com.car_shop.entity.User;
import com.car_shop.validator.Validator;
import com.car_shop.validator.user.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserLoginValidator implements Validator
{
    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserLoginValidator(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public void validate(Object o) throws Exception
    {
        User user = (User) o;

        if (user.getName().isEmpty())
        {
            throw new UserException(UserLoginValidationMessages.EMPTY_USERNAME_FIELD);
        }
        else if(user.getPassword().isEmpty())
        {
            throw  new UserException(UserLoginValidationMessages.EMPTY_PASSWORD_FIELD);
        }
        else if (userDao.findByName(user.getName()) == null)
        {
            throw new UserException(UserLoginValidationMessages.WRONG_USERNAME_OR_PASSWORD);
        }
        else if (encoder.matches(user.getPassword(),
                userDao.findByName(user.getName()).getPassword()))
        {
            throw new UserException(UserLoginValidationMessages.WRONG_USERNAME_OR_PASSWORD);
        }
    }
}
