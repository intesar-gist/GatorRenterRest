package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;

/**
 * Created by Intesar on 3/5/2017.
 */
public interface UserService {

    ResponseDto addUser(UserDto userDto);
    ResponseDto getUserDetailsForLogin(String email);
    ResponseDto logoutUser(Integer userId);
//    ResponseDto updateUser(UserDto userDto);

}
