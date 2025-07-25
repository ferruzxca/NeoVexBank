package com.innovex.neovexbank.service;

import com.innovex.neovexbank.model.Customer;
import com.innovex.neovexbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByUserId(Long userId) {
        return customerRepository.findByUserId(userId);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}