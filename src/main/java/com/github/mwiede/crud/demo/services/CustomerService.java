package com.github.mwiede.crud.demo.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.github.mwiede.crud.demo.model.Customer;

@ApplicationScoped
public class CustomerService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Customer> getCustomers() {
        return entityManager.createQuery("select c from Customer c").getResultList();
    }

    @Transactional
    public Customer update(Customer currentCustomer) {
       return entityManager.merge(currentCustomer);
    }
}
