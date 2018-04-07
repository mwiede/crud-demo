package com.github.mwiede.crud.demo.jaxrs;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.mwiede.crud.demo.services.CustomerService;
import com.github.mwiede.crud.demo.model.Customer;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

}
