package com.crud.orders.service;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.entity.CustomerEntity;
import com.crud.orders.exception.CustomerAlreadyRegisteredException;
import com.crud.orders.exception.CustomerNotFoundException;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Long registerCustomer(CustomerDTO customerDTO) {
        if(checkCustomersExistance(customerDTO))
            throw new CustomerAlreadyRegisteredException(
                    customerDTO.getName(),
                    customerDTO.getAddress());

        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customerDTO.getName());
        newCustomer.setAddress(customerDTO.getAddress());

        em.persist(newCustomer);
        em.flush();

        return newCustomer.getId();
    }

    public CustomerEntity getCustomer(CustomerDTO customerDTO) {
        Query q = em.createNamedQuery("CustomerEntity.findByNameAddressPair");
        q.setParameter("name", customerDTO.getName());
        q.setParameter("address", customerDTO.getAddress());
//        CustomerEntity customer = (CustomerEntity) q.getSingleResult();
        CustomerEntity customerEntity = new CustomerEntity();
        try {
            customerEntity = (CustomerEntity) q.getSingleResult();
        } catch (NoResultException noResEsc) {
            throw new CustomerNotFoundException(customerDTO.getName(), customerDTO.getAddress());
        }
        return customerEntity;
    }

    public boolean checkCustomersExistance(CustomerDTO customerDTO) {
        Query q = em.createNamedQuery("CustomerEntity.findByNameAddressPair");
        q.setParameter("name", customerDTO.getName());
        q.setParameter("address", customerDTO.getAddress());

        long count = q.getResultStream().count();

        return count == 1;
    }
}
