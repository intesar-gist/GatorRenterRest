package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.business.UserService;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Intesar on 3/5/2017.
 */
@Path("/user")
@Component
public class UserController {

    @Autowired
    @Qualifier("gatorRenterAuthentication")
    Authentication authentication;

    @Autowired
    private UserService userService;

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/addNewUser")
    public Response addNewUser(UserDto userDto) {

        ResponseDto responseDto = userService.addUser(userDto);

        return Response.ok().entity(responseDto).build();

    }
}