package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/5/2017.
 */
@Transactional
public interface UserTokenManager {

    UserTokenDto addToken(UserDto userDto) throws GatorRenterException;
    UserTokenDto findByAccessTokenAndUserId(Integer userId, String accessToken) throws GatorRenterException;
    void removeUserTokens(UserDto userDto) throws GatorRenterException;


}
