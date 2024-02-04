package com.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hibernate.dao.CustomerDAO;
import com.hibernate.entity.Customer;

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
	
	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCustomer() {
		
		List<Customer> customers = CustomerDAO.getInstant().findAll();
		System.out.print("customers: "+customers.size());
		
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(customers).build();
		
	}
}
