package com.crud.orders.service;

import com.crud.orders.entity.ProductsEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProductsService {
    @PersistenceContext
    @Inject
    EntityManager em;

    @Transactional
    public boolean registerProduct(String name, String code) {
        ProductsEntity newProduct = new ProductsEntity();

        newProduct.setName(name);
        newProduct.setCode(code);

        em.persist(newProduct);

        return true;
    }
}
