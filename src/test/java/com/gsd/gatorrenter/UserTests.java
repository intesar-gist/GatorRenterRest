package com.gsd.gatorrenter;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.authentication.GatorRenterAuthentication;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.manager.UserManager;
import com.gsd.gatorrenter.manager.UserTokenManager;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserTests {

    UserDto userDto = new UserDto("$2a$12$mHYX224P75GkN8zXcZ382untmc0fuUwGAXx1u5mcZh39vE0u9XYTq");
    UserTokenDto userTokenDto = new UserTokenDto("TEST-USER-TOKEN");

    @InjectMocks //@InjectMocks annotation is used to create and inject the mock object
    Authentication authentication = new GatorRenterAuthentication();

    @Mock //@Mock annotation is used to create the mock object to be injected
    UserManager userManager;

    @Mock
    UserTokenManager userTokenManager;


    @Before
    public void init() {

    }

    @Test
    public void test_authenticateUserCredentials() throws Exception {
        Mockito
                .when(userManager.findByEmail("intesar.haider@gmail.com"))
                .thenReturn(userDto);

        Boolean test = authentication.authenticate("intesar.haider@gmail.com", "12345678");
        Assert.assertTrue("Return is false, couldn't authenticate user", test);

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userManager).findByEmail("intesar.haider@gmail.com");
    }

    @Test
    public void test_authorizeUserByToken() throws Exception {

        Mockito
                .when(userTokenManager.findByAccessTokenAndUserId(1, "TEST-USER-TOKEN"))
                .thenReturn(userTokenDto);

        Boolean test = authentication.authenticate(1, "TEST-USER-TOKEN");
        Assert.assertTrue("Return is false, couldn't authorized token", test);

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userTokenManager).findByAccessTokenAndUserId(1, "TEST-USER-TOKEN");
    }

    @Test(expected=GatorRenterException.class)
    public void test_unauthorizeUserDueToEmptyToken() throws Exception {

        Mockito
                .when(userTokenManager.findByAccessTokenAndUserId(1, ""))
                .thenThrow(new GatorRenterException(ResponseStatusCode.UNAUTHENTICATED_CLIENT));

        Boolean test = authentication.authenticate(1, "");

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userTokenManager).findByAccessTokenAndUserId(1, "");

        if(!test) {
            throw new GatorRenterException(ResponseStatusCode.UNAUTHENTICATED_CLIENT);
        }
    }

    @Test
    public void test_passwordHashingMechanism() throws Exception {

        String plainPwd = "123";
        String hashPwd = authentication.hashPassword(plainPwd);
        Boolean isPwdSimilar = authentication.isPwdSimilar(plainPwd, hashPwd);

        Assert.assertTrue("Wrong hash, plain password couldn't be verified after hash", isPwdSimilar);
    }

    @Test
    public void test_passwordHashingMechanismFails() throws Exception {

        String plainPwd = "123";
        String hashPwd = authentication.hashPassword(plainPwd);
        Boolean isPwdSimilar = authentication.isPwdSimilar(plainPwd, hashPwd+"888");

        Assert.assertTrue("Wrong hash, plain password couldn't be verified after hash", isPwdSimilar);
    }
}
