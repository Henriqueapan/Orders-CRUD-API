package com.crud.orders.service;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.entity.CustomerEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomersService {
    @PersistenceContext
    @Inject
    EntityManager em;

    private final org.jboss.logging.Logger LOG = Logger.getLogger(this.getClass());

    @Transactional
    public boolean registerCustomer(CustomerDTO customerDTO) {
        if(checkCustomersExistance(customerDTO)) return false;
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customerDTO.getName());
        newCustomer.setAddress(customerDTO.getAddress());

        em.persist(newCustomer);

        return true;
    }

    public CustomerEntity getCustomer(CustomerDTO customerDTO) {
        Query q = em.createNamedQuery("CustomerEntity.findByNameAddressPair");
        q.setParameter("name", customerDTO.getName());
        q.setParameter("address", customerDTO.getAddress());
        CustomerEntity customer = (CustomerEntity) q.getSingleResult();

        return customer;
    }

    public boolean checkCustomersExistance(CustomerDTO customerDTO) {
        Query q = em.createNamedQuery("CustomerEntity.findByNameAddressPair");
        q.setParameter("name", customerDTO.getName());
        q.setParameter("address", customerDTO.getAddress());

        long count = q.getResultStream().count();

        return count == 1;
    }
}
