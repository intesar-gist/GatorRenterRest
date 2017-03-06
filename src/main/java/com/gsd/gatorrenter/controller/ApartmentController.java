package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.manager.ApartmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/apartment")
@Component
public class ApartmentController {

	@Autowired
	private ApartmentManager apartmentManager;

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/getApartment/{apartmentId}")
	public Response getApartment(@PathParam("apartmentId") Integer apartmentId) {
		ApartmentDto newoo = null;
		try {
			ApartmentDto apartmentDto = apartmentManager.getApartmentById(apartmentId);
			 newoo = apartmentDto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.ok().entity(newoo).build();

	}
}