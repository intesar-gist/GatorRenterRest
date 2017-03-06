package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.entity.UserToken;
import com.gsd.gatorrenter.manager.UserManager;
import com.gsd.gatorrenter.manager.UserTokenManager;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/5/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserTokenManager userTokenManager;

    @Autowired
    private Authentication authentication;

    @Override
    public ResponseDto addUser(UserDto userDto)  {

        try {

            verifyData(userDto);

            userDto = userManager.addNewUser(userDto);

            ResponseDto responseDto = ResponseDto.createSuccessResponse();
            responseDto.setUserDto(userDto);

            return responseDto;

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }

    @Override
    public ResponseDto logoutUser(Integer userId) {

        try {

            UserDto userDto = userManager.findUserDtoById(userId);

            if(EntityHelper.isNull(userDto)) {
                throw new GatorRenterException(ResponseStatusCode.USER_NOT_FOUND, userId);
            }

            //removing all the active tokens
            userTokenManager.removeUserTokens(userDto);

            ResponseDto responseDto = ResponseDto.createSuccessResponse();
            return responseDto;

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }

    @Override
    public ResponseDto getUserDetailsForLogin(String email) {

        try {

            UserDto user = userManager.findByEmail(email);

            if(EntityHelper.isNull(user)) {
                throw new GatorRenterException(ResponseStatusCode.USER_NOT_FOUND, email);
            }

            //generating token and returning back with user details for login
            UserTokenDto userTokenDto = userTokenManager.addToken(user);

            ResponseDto responseDto = ResponseDto.createSuccessResponse();
            responseDto.setUserTokenDto(userTokenDto);

            return responseDto;

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }

    private void verifyData(UserDto userDto) throws GatorRenterException {

        if(!EntityHelper.isSet(userDto.getEmail()))
            throw new GatorRenterException(ResponseStatusCode.EMAIL_MISSING);

        if(!EntityHelper.isSet(userDto.getPassword())) {
            throw new GatorRenterException(ResponseStatusCode.PWD_MISSING);
        } else {
            //hashing password
            userDto.setPassword(authentication.hashPassword(userDto.getPassword()));
        }

        if(EntityHelper.isNull(userDto.getUserRoleDto()))
            throw new GatorRenterException(ResponseStatusCode.NO_ROLE_DEFINED);

        UserDto existingUser = userManager.findByEmail(userDto.getEmail());
        if(EntityHelper.isNotNull(existingUser)) //mean user exits
            throw new GatorRenterException(ResponseStatusCode.USER_ALREADY_EXISTS);

    }

}
