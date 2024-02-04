package com.hibernate.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hibernate.dao.CustomerDAO;

@Path("customer")
public class CustomerController {

	@GET
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() {
		return "hello world";
	}
	
	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public String createCustomer() {
		
		CustomerDAO.getInstant().testSave();
		
		return "hello world";
	}
	
	@GET
	@Path("/update/{id}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCustomer(@PathParam("id") String id, @PathParam("name") String userName) {
		
		CustomerDAO.getInstant().testUpdate(id, userName);
		
		return "hello world";
	}
}
