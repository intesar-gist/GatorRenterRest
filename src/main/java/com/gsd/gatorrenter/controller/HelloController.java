package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.manager.ApartmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
@Component
public class HelloController {

	@Autowired
	private ApartmentManager apartmentManager;

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/hello")
	public Response getHello() {
		ApartmentDto newoo = null;
		try {
			ApartmentDto apartmentDto = apartmentManager.getApartmentById(1);
			 newoo = apartmentDto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.ok().entity(newoo).build();

	}
}