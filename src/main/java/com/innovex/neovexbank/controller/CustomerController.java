package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.Customer;
import com.innovex.neovexbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Obtener cliente por ID de usuario (para vincular usuario con perfil)
    @GetMapping("/user/{userId}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable Long userId) {
        return customerRepository.findByUserId(userId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo cliente
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setUpdatedAt(java.time.LocalDateTime.now());
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    // Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
            .map(customer -> {
                customer.setName(customerDetails.getName());
                customer.setAddress(customerDetails.getAddress());
                customer.setPhone(customerDetails.getPhone());
                customer.setEmail(customerDetails.getEmail());
                customer.setUpdatedAt(java.time.LocalDateTime.now());
                return ResponseEntity.ok(customerRepository.save(customer));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
            .map(customer -> {
                customerRepository.delete(customer);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}