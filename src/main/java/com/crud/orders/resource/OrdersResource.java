package com.crud.orders.resource;

import com.crud.orders.dto.OrderDTO;
import com.crud.orders.dto.UpdateDeliveryStatusDTO;
import com.crud.orders.exception.ProductNotRegisteredException;
import com.crud.orders.service.OrdersService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/orders")
public class OrdersResource {
    @Inject
    OrdersService ordersService;

    @Path("register-order")
    @POST
    public Response registerOrder(@Valid OrderDTO orderDTO) throws ProductNotRegisteredException {
        Long orderId = ordersService.registerOrder(orderDTO);
        return Response.status(Response.Status.CREATED)
            .entity("OK")
            .location(URI.create("/orders/" + orderId))
            .build();
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
    public Response updateDeliveryStatus(@PathParam("oid") Long id, @Valid UpdateDeliveryStatusDTO updateDeliveryStatusDTO) {
        // Error handling and Exception throwing is done by the Service class
        return Response.ok(ordersService.updateDeliveryStatus(id, updateDeliveryStatusDTO.getDelivery_status())).build();
    }
}
