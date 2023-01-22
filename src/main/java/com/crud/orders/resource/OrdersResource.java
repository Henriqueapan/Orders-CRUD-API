package com.crud.orders.resource;

import com.crud.orders.dto.OrderDTO;
import com.crud.orders.exception.ProductNotRegisteredException;
import com.crud.orders.service.OrdersService;

import javax.inject.Inject;
import javax.transaction.*;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrdersResource {
    @Inject
    OrdersService ordersService;

    @Path("register-order")
    @POST
    public Response registerOrder(@Valid OrderDTO orderDTO) throws ProductNotRegisteredException {
        return Response.ok(ordersService.registerOrder(orderDTO)).build();
    }

    @Path("/{oid}")
    @GET
    public Response getOrder(@PathParam("oid") String id) {
        return Response.ok(ordersService.getOrder(id)).build();
    }
}
