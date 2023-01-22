package com.crud.orders.service;

import com.crud.orders.dto.ProductDTO;
import com.crud.orders.entity.ProductsEntity;
import com.crud.orders.exception.ProductAlreadyRegisteredException;
import com.crud.orders.exception.ProductNotRegisteredException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProductsService {
    @PersistenceContext
    @Inject
    EntityManager em;

    @Transactional
    public boolean registerProduct(ProductDTO productDTO) {
        if(_checkProductsExistance(productDTO)) {
            throw new ProductAlreadyRegisteredException(productDTO.getCode());
        }

        ProductsEntity newProduct = new ProductsEntity();
        newProduct.setName(productDTO.getName());
        newProduct.setCode(productDTO.getCode());

        em.persist(newProduct);

        return true;
    }

    public ProductsEntity getProduct(String productCode){
        ProductsEntity productsEntity = new ProductsEntity();
        try {
            productsEntity = (ProductsEntity) em.createNamedQuery("ProductsEntity.findByCode")
                    .setParameter("code", productCode)
                    .getSingleResult();
        } catch (NoResultException noResExc) {
            throw new ProductNotRegisteredException(productCode);
        }
        return productsEntity;
    }

    private boolean _checkProductsExistance(ProductDTO productDTO) {
        long qCount = em.createNamedQuery("ProductsEntity.findByCode")
                .setParameter("code", productDTO.getCode())
                .getResultStream()
                .count();

        return qCount == 1;
    }
}
