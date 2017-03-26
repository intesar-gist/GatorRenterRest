package com.gsd.gatorrenter;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.authentication.GatorRenterAuthentication;
import com.gsd.gatorrenter.business.UserService;
import com.gsd.gatorrenter.business.UserServiceImpl;
import com.gsd.gatorrenter.dto.ResponseDto;
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
public class SecurityModuleTests {

    UserDto userDto = new UserDto("$2a$12$mHYX224P75GkN8zXcZ382untmc0fuUwGAXx1u5mcZh39vE0u9XYTq");
    UserTokenDto userTokenDto = new UserTokenDto("TEST-USER-TOKEN");

    @InjectMocks //@InjectMocks annotation is used to create and inject the mock object
    Authentication authentication = new GatorRenterAuthentication();

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Mock //@Mock annotation is used to create the mock object to be injected
    UserManager userManager;

    @Mock
    UserTokenManager userTokenManager;


    @Before
    public void init() {

    }


    /**********************************
     * CREDENTIAL AUTHENTICATION START
     * ********************************
     */
    @Test
    public void test_authenticateUserCredentials_ShouldPass_CorrectCredential() throws Exception {
        Mockito
                .when(userManager.findByEmail("intesar.haider@gmail.com"))
                .thenReturn(userDto);

        Boolean test = authentication.authenticate("intesar.haider@gmail.com", "12345678");
        Assert.assertTrue("Return is false, couldn't authenticate user", test);

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userManager).findByEmail("intesar.haider@gmail.com");
    }

    @Test
    public void test_authenticateUserCredentials_ShouldFail_wrongCredential() throws Exception {
        Mockito
                .when(userManager.findByEmail("intesar.haider@gmail.com"))
                .thenReturn(userDto);

        Boolean test = authentication.authenticate("intesar.haider@gmail.com", "123456789");
        Assert.assertFalse("Return is TRUE, even though wrong credentials", test);
    }

    @Test
    public void test_authenticateUserCredentials_ShouldFail_wrongEmailFormat() throws Exception {
        Mockito
                .when(userManager.findByEmail("intesar.haider@gmail.com"))
                .thenReturn(userDto);

        Boolean test = authentication.authenticate("intesar.haider@gmailcom", "12345678");
        Assert.assertFalse("Return is True, user got authenticated using wrong email", test);
    }

    /***************************
     * PASSWORD HASHING
     * *************************
     */

    @Test
    public void test_passwordHashingMechanism_ShouldPass_HashAndPlainPwdMatches() throws Exception {

        String plainPwd = "123";
        String hashPwd = authentication.hashPassword(plainPwd);
        Boolean isPwdSimilar = authentication.isPwdSimilar(plainPwd, hashPwd);

        Assert.assertTrue("Wrong hash, plain password couldn't be verified after hash", isPwdSimilar);
    }

    @Test
    public void test_passwordHashingMechanism_ShouldFail_HashAndPlainPwdDoesNotMatch() throws Exception {

        String plainPwd = "123";
        String hashPwd = authentication.hashPassword(plainPwd);
        Boolean isPwdSimilar = authentication.isPwdSimilar(plainPwd, hashPwd + "888");

        Assert.assertFalse("Wrong hash, but still pwd got verified", isPwdSimilar);
    }

    /***************************
     * TOKEN AUTHORIZATION START
     * *************************
     */
    @Test
    public void test_authorizeUserByToken_ShouldPass_correctUserIdAndTokenCombination() throws Exception {

        Mockito
                .when(userTokenManager.findByAccessTokenAndUserId(1, "TEST-USER-TOKEN"))
                .thenReturn(userTokenDto);

        Boolean test = authentication.authenticate(1, "TEST-USER-TOKEN");
        Assert.assertTrue("Return is false, couldn't authorized token", test);

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userTokenManager).findByAccessTokenAndUserId(1, "TEST-USER-TOKEN");
    }

    @Test
    public void test_authorizeUserByToken_ShouldFail_incorrectUserIdAndTokenCombination() throws Exception {

        Mockito
                .when(userTokenManager.findByAccessTokenAndUserId(1, "TEST-USER-TOKEN"))
                .thenReturn(userTokenDto);

        Boolean test = authentication.authenticate(2, "TEST-USER-TOKEN");
        Assert.assertFalse("Return is TRUE, even though combination is wrong", test);

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userTokenManager).findByAccessTokenAndUserId(2, "TEST-USER-TOKEN");
    }

    @Test(expected=GatorRenterException.class)
    public void test_authorizeUserByToken_ShouldFail_DueToEmptyToken() throws Exception {

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

    /***************************
     * LOGOUT TESTING START
     * *************************
     */
    @Test
    public void test_logoutUser_ShouldPass_correctUserIdGiven() throws Exception {

        Mockito
                .when(userManager.findUserDtoById(1))
                .thenReturn(userDto);

        ResponseDto responseDto = userService.logoutUser(1);
        Assert.assertTrue("Unable to logout user", responseDto.getStatusDto().getSuccess());

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userManager).findUserDtoById(1);
    }

    @Test
    public void test_logoutUser_ShouldFail_wrongUserIdGiven() throws Exception {

        Mockito
                .when(userManager.findUserDtoById(1))
                .thenReturn(userDto);

        ResponseDto responseDto = userService.logoutUser(2);
        Assert.assertFalse("Failed because successfully logged out even though wrong id was given", responseDto.getStatusDto().getSuccess());

        //to ensure whether a mock method is being called with required arguments or not (to pass the test)
        Mockito.verify(userManager).findUserDtoById(2);
    }
}
