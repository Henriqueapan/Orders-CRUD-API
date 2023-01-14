package com.crud.orders.resource;

import com.crud.orders.service.OrdersService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrdersResource {
    @Inject
    OrdersService ordersService;

    @Path("/{oid}")
    @GET
    public Response getOrder(@PathParam("oid") String id) {
        return Response.ok(ordersService.getOrder(id)).build();
    }
}
