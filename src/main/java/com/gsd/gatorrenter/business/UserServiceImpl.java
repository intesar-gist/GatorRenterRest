package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
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

            userTokenManager.addToken(userDto);

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
