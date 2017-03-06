package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.authentication.Authentication;
import com.gsd.gatorrenter.business.UserService;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Intesar on 3/5/2017.
 */
@Path("/user")
@Component
public class UserController {

    @Autowired
    Authentication authentication;

    @Autowired
    private UserService userService;

    private Boolean authenticateClient(Integer signedInUserId, String accessToken) {

        if (!authentication.authenticate(signedInUserId, accessToken)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/addNewUser")
    public Response addNewUser(@HeaderParam("signedInUserId") Integer signedInUserId, @HeaderParam("accessToken") String accessToken, UserDto userDto) {

        if(!authenticateClient(signedInUserId, accessToken)) {
            return ResponseDto.unauthenticClientResponse();
        }

        ResponseDto responseDto = userService.addUser(userDto);
        return Response.ok().entity(responseDto).build();

    }
}