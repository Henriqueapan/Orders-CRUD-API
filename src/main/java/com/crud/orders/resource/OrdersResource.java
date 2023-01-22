package com.crud.orders.resource;

import com.crud.orders.dto.ErrorResponseDTO;
import com.crud.orders.dto.OrderDTO;
import com.crud.orders.exception.OrderNotFoundException;
import com.crud.orders.exception.ProductNotRegisteredException;
import com.crud.orders.service.OrdersService;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.transaction.*;
import javax.validation.Valid;
import javax.ws.rs.*;
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
    public Response getOrder(@PathParam("oid") Long id) {
        return Response.ok(ordersService.getOrder(id)).build();
    }

    @GET
    public Response getAllOrders() {
        return Response.ok(ordersService.getAllOrders()).build();
    }

    @Path("/{oid}/update-delivery-status")
    @PUT
    public Response updateDeliveryStatus(@PathParam("oid") Long id, @Valid OrderDTO orderDTO) {
        // Error handling and Exception throwing is done by the Service class
        return Response.ok(ordersService.updateDeliveryStatus(id, orderDTO.getDelivery_status())).build();
    }
}
