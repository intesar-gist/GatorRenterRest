package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.business.ApartmentService;
import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.manager.ApartmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Intesar on 3/5/2017.
 */
@Path("/apartment")
@Component
public class ApartmentController extends BaseController {

	@Autowired
	private ApartmentService apartmentService;

	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	@Path("/addNewApartment")
	public Response addNewApartment(@HeaderParam("signedInUserId") Integer signedInUserId,
									@HeaderParam("accessToken") String accessToken,
									ApartmentDto apartmentDto) {

		if(!authenticateClientToken(signedInUserId, accessToken)) {
			return ResponseDto.unauthenticClientResponse();
		}

		ResponseDto responseDto = apartmentService.addNewApartment(apartmentDto);
		return Response.ok().entity(responseDto).build();

	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	@Path("/updateApartment")
	public Response updateApartment(@HeaderParam("signedInUserId") Integer signedInUserId,
									@HeaderParam("accessToken") String accessToken,
									ApartmentDto apartmentDto) {

		if(!authenticateClientToken(signedInUserId, accessToken)) {
			return ResponseDto.unauthenticClientResponse();
		}

		ResponseDto responseDto = apartmentService.updateApartment(apartmentDto);
		return Response.ok().entity(responseDto).build();

	}

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/getApartmentsByOwner/{ownerId}")
	public Response getUserApartments(@HeaderParam("signedInUserId") Integer signedInUserId,
									  @HeaderParam("accessToken") String accessToken,
									  @PathParam("ownerId") Integer userId) {

		if(!authenticateClientToken(signedInUserId, accessToken)) {
			return ResponseDto.unauthenticClientResponse();
		}

		ResponseDto responseDto = apartmentService.getApartmentsByUserId(userId);
		return Response.ok().entity(responseDto).build();

	}

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/getApartment/{apartmentId}")
	public Response getApartment(@PathParam("apartmentId") Integer apartmentId) {

		return null;

	}
}