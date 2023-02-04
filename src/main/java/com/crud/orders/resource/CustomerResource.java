package com.crud.orders.resource;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.dto.ErrorResponseDTO;
import com.crud.orders.service.CustomersService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/customers")
public class CustomerResource {
    @Inject
    CustomersService customersService;

    @Path("/register-customer")
    @POST
    public Response registerCustomer(CustomerDTO customerDTO) {
        Long customerId = customersService.registerCustomer(customerDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity("OK")
                .location(URI.create("/customers/" + customerId))
                .build();
    }

    @GET
    @SuppressWarnings("all")
    public Response getCustomerList(@QueryParam("name") String name, @QueryParam("address") String address) {
        if((name == null && address != null) || (name != null && address == null)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of(
                    "error",
                    new ErrorResponseDTO("Invalid Query Parameters", "One of the path " +
                            "parameters is null.")
            )).build();
        }
        else if((name == null && address == null) || (name.isBlank() && address.isBlank())) {
            return Response.ok(customersService.getAllCustomers()).build();
        } else if ((name.isBlank() && !address.isBlank()) || (!name.isBlank() && address.isBlank())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of(
                    "error",
                    new ErrorResponseDTO("Invalid Query Parameters", "One of the path " +
                            "parameters is blank.")
            )).build();
        }
        return Response.ok(customersService.getCustomerByNameAddressPair(name, address)).build();
    }

    @Path("/{id}")
    @GET
    public Response getCustomerById(@PathParam("id") long customerId) {
        return Response.ok(customersService.getCustomerById(customerId)).build();
    }
}
