package com.crud.orders.service;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.entity.CustomerEntity;
import com.crud.orders.exception.CustomerAlreadyRegisteredException;
import com.crud.orders.exception.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomersService {
    @PersistenceContext
    @Inject
    EntityManager em;

    private final ObjectMapper mapper = new ObjectMapper();

    private final org.jboss.logging.Logger LOG = Logger.getLogger(this.getClass());

    @Transactional
    public Long registerCustomer(CustomerDTO customerDTO) {
        if(checkCustomersExistance(customerDTO))
            throw new CustomerAlreadyRegisteredException(
                    customerDTO.getName(),
                    customerDTO.getAddress());

        CustomerEntity newCustomer = mapper.convertValue(customerDTO, CustomerEntity.class);

        em.persist(newCustomer);
        em.flush();

        return newCustomer.getId();
    }

    public List<CustomerEntity> getAllCustomers(){
        Query q = em.createQuery("SELECT c FROM CustomerEntity c");
        List<CustomerEntity> customerEntities;
        try {
            customerEntities =  q.getResultList();
        } catch (NoResultException noResExc) {
            return List.of();
        }
        return customerEntities;
    }

    public CustomerEntity getCustomerByNameAddressPair(String name, String address) {
        Query q = em.createNamedQuery("CustomerEntity.findByNameAddressPair");
        q.setParameter("name", name);
        q.setParameter("address", address);
//        CustomerEntity customer = (CustomerEntity) q.getSingleResult();
        CustomerEntity customerEntity = new CustomerEntity();
        try {
            customerEntity = (CustomerEntity) q.getSingleResult();
        } catch (NoResultException noResExc) {
            throw new CustomerNotFoundException(name, address);
        }
        return customerEntity;
    }

    public CustomerEntity getCustomerById(Long id) {
        Query q = em.createNamedQuery("CustomerEntity.findById").setParameter("id", id);
        CustomerEntity customerEntity = new CustomerEntity();

        try {
            customerEntity = (CustomerEntity) q.getSingleResult();
        } catch (NoResultException noResExc) {
            throw new CustomerNotFoundException(id);
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
