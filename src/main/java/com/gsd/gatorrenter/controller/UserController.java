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
public class UserController extends BaseController {

    @Autowired
    Authentication authentication;

    @Autowired
    private UserService userService;

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/addNewUser")
    public Response addNewUser(@HeaderParam("signedInUserId") Integer signedInUserId,
                               @HeaderParam("accessToken") String accessToken,
                               UserDto userDto) {

        if(!authenticateClientToken(signedInUserId, accessToken)) {
            return ResponseDto.unauthenticClientResponse();
        }

        ResponseDto responseDto = userService.addUser(userDto);
        return Response.ok().entity(responseDto).build();

    }

    @PUT
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/updateUser")
    public Response updateUser(@HeaderParam("signedInUserId") Integer signedInUserId,
                               @HeaderParam("accessToken") String accessToken,
                               UserDto userDto) {

        if(!authenticateClientToken(signedInUserId, accessToken)) {
            return ResponseDto.unauthenticClientResponse();
        }

        ResponseDto responseDto = userService.updateUser(userDto);
        return Response.ok().entity(responseDto).build();

    }

    @DELETE
    @Produces({MediaType.APPLICATION_XML})
    @Path("/deleteUser/{userId}")
    public Response deleteUser(@PathParam("userId") Integer userId,
                               @HeaderParam("signedInUserId") Integer signedInUserId,
                               @HeaderParam("accessToken") String accessToken) {

        if(!authenticateClientToken(signedInUserId, accessToken)) {
            return ResponseDto.unauthenticClientResponse();
        }

        ResponseDto responseDto = userService.deleteUser(userId);
        return Response.ok().entity(responseDto).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/login")
    public Response login(@HeaderParam("email") String email, @HeaderParam("password") String password) {

        if (!authentication.authenticate(email, password)) {
            return ResponseDto.unauthenticClientResponse();
        }

        ResponseDto responseDto = userService.getUserDetailsForLogin(email);
        return Response.ok().entity(responseDto).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/logout")
    public Response login(@HeaderParam("signedInUserId") Integer signedInUserId) {

        ResponseDto responseDto = userService.logoutUser(signedInUserId);
        return Response.ok().entity(responseDto).build();

    }
}