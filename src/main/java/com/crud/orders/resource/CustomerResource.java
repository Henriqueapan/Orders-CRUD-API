package com.crud.orders.resource;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.service.CustomersService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {
    @Inject
    CustomersService customersService;

    @Path("/register-customer")
    @POST
    public Response registerCustomer(CustomerDTO customerDTO) {
        return Response.ok(customersService.registerCustomer(customerDTO)).build();
    }

    @GET
    public Response getCustomer(@QueryParam("name") String name, @QueryParam("address") String address) {
        CustomerDTO customerDTO = new CustomerDTO(name, address);
        return Response.ok(customersService.getCustomer(customerDTO)).build();
    }
}
