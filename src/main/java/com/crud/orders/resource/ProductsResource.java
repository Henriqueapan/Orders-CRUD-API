package com.crud.orders.resource;

import com.crud.orders.dto.ProductDTO;
import com.crud.orders.service.ProductsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/products")
public class ProductsResource {
    @Inject
    ProductsService productsService;

    @Path("/register-product")
    @POST
    public Response registerProduct(@Valid ProductDTO productDTO){
        String newProductCode = productsService.registerProduct(productDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity("OK")
                .location(URI.create("/products/" + newProductCode))
                .build();
    }

    @Path("/{pcode}")
    @GET
    public Response getProduct(@PathParam("pcode") String productCode){
        return Response
                .ok(
                        productsService.getProduct(productCode)
                )
                .build();
    }

    @GET
    public Response getAllProducts() {
        return Response.ok(productsService.getAllProducts()).build();
    }
}
