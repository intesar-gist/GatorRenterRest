package com.gsd.gatorrenter;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.authentication.GatorRenterAuthentication;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.manager.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {

    UserDto userDto = new UserDto("$2a$12$mHYX224P75GkN8zXcZ382untmc0fuUwGAXx1u5mcZh39vE0u9XYTq");
    User user = new User();

    @InjectMocks
    Authentication authentication = new GatorRenterAuthentication();

    @Mock
    UserManager userManager;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito
                .when(userManager.findByEmail("intesar.haider@gmail.com"))
                .thenReturn(userDto);

    }

    @Test
    public void simple() throws Exception {
        Boolean test = authentication.authenticate("intesar.haider@gmail.com", "12345678");
        System.out.println(test);
    }
}
