package com.gsd.gatorrenter.authentication;
import org.apache.commons.lang3.Validate.*;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.manager.UserManager;
import com.gsd.gatorrenter.manager.UserTokenManager;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.commons.validator.routines.EmailValidator;
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
    public Boolean authenticate(String email, String password) {


        EmailValidator emailvalidator = EmailValidator.getInstance();

        if(emailvalidator.isValid(email)) {

            UserDto user = userManager.findByEmail(email);
            if (EntityHelper.isNotNull(user)) {
                return isPwdSimilar(password, user.getPassword());
            }

        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean isPwdSimilar(String plainPwd, String hashedPwd) {
        if(EntityHelper.isSet(plainPwd) && EntityHelper.isSet(hashedPwd)) {
            if (BCrypt.checkpw(plainPwd, hashedPwd)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean authenticate(Integer userId, String accessToken) {
        try {

            UserTokenDto userToken = userTokenManager.findByAccessTokenAndUserId(userId, accessToken);

            if (EntityHelper.isNotNull(userToken)) {
                return Boolean.TRUE;
            }

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
        }

        return Boolean.FALSE;
    }

    @Override
    public String hashPassword(String passwordPlaintext) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt(12));
    }





}
