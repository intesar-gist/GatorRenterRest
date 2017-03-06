package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/5/2017.
 */
@Transactional
public interface UserManager {

    UserDto findByEmail(String userEmail);
    UserDto findUserDtoById(Integer userId);
    User findById(Integer userId);
    UserDto addNewUser(UserDto userDto) throws GatorRenterException;

}
