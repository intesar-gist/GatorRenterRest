package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.authentication.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Intesar on 3/6/2017.
 */
@Component
public class BaseController {

    @Autowired
    Authentication authentication;

    protected Boolean authenticateClientToken(Integer signedInUserId, String accessToken) {

        if (!authentication.authenticate(signedInUserId, accessToken)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
