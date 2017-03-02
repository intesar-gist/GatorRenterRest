package com.gsd.gatorrenter.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("spring-hello")
@Component
public class HelloController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello() {
		return "hello world";
	}
}