package com.gsd.gatorrenter.authentication;

/**
 * Created by Intesar on 3/5/2017.
 */
public interface Authentication {

    Boolean authenticate(String userName, String password);
    Boolean authenticate(Integer userId, String accessToken);
    String hashPassword(String passwordPlaintext);

}
