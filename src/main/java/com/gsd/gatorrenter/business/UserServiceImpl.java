package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.entity.User;
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

            //hashing password
            userDto.setPassword(authentication.hashPassword(userDto.getPassword()));

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
    public ResponseDto updateUser(UserDto userDto)  {

        try {

            User user = verifyDataAndGetUser(userDto);

            //if different password passed, update it
            if(!authentication.isPwdSimilar(userDto.getPassword(), user.getPassword())){
                user.setPassword(authentication.hashPassword(userDto.getPassword()));
            }

            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setCity(userDto.getCity());

            userManager.updateUser(user);

            return ResponseDto.createSuccessResponse();

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
    public ResponseDto deleteUser(Integer userId) {

        try {

            User user = userManager.findById(userId);

            if(EntityHelper.isNull(user)) {
                throw new GatorRenterException(ResponseStatusCode.USER_NOT_FOUND, userId);
            }

            userManager.deleteUser(user);
            userTokenManager.removeUserTokens(new UserDto(userId));

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

    private User verifyDataAndGetUser(UserDto userDto) throws GatorRenterException {

        if(!EntityHelper.isSet(userDto.getId())) {
            throw new GatorRenterException(ResponseStatusCode.MISSING_USER_ID);
        }

        if(!EntityHelper.isSet(userDto.getEmail())) {
            throw new GatorRenterException(ResponseStatusCode.EMAIL_MISSING);
        }

        if(!EntityHelper.isSet(userDto.getPassword())) {
            throw new GatorRenterException(ResponseStatusCode.PWD_MISSING);
        }

        User user = userManager.findById(userDto.getId());

        if(EntityHelper.isNull(user)) {
            throw new GatorRenterException(ResponseStatusCode.USER_NOT_FOUND, userDto.getId());
        }

        return user;

    }

    private void verifyData(UserDto userDto) throws GatorRenterException {

        if(!EntityHelper.isSet(userDto.getEmail())) {
            throw new GatorRenterException(ResponseStatusCode.EMAIL_MISSING);
        }

        if(!EntityHelper.isSet(userDto.getPassword())) {
            throw new GatorRenterException(ResponseStatusCode.PWD_MISSING);
        }

        if(EntityHelper.isNull(userDto.getUserRoleDto())) {
            throw new GatorRenterException(ResponseStatusCode.NO_ROLE_DEFINED);
        }

        UserDto existingUser = userManager.findByEmail(userDto.getEmail());
        if(EntityHelper.isNotNull(existingUser)) { //mean user exits
            throw new GatorRenterException(ResponseStatusCode.USER_ALREADY_EXISTS);
        }

    }

}
