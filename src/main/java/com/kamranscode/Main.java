package com.kamranscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {



    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}

    record UpdateCustomerRequest(
            String name,
            String email,
            Integer age,
            Integer id
    ){}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("{customerId}")
    public void getCustomer(@PathVariable("customerId") Integer id) {
        Optional<Customer> oldCustomer = customerRepository.findById(id);
        oldCustomer.ifPresent(customer -> System.out.println(customer.getId()));
    }

    @PutMapping
    public Optional<Customer> updateCustomer(@RequestBody UpdateCustomerRequest request) {

        Optional<Customer> oldCustomer = customerRepository.findById(request.id);
        Customer customer = new Customer();

        if(oldCustomer.isPresent()) {
            customer = oldCustomer.get();
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setAge(request.age());
            customerRepository.save(customer);

            return Optional.of(customer);
        } else {
            return Optional.empty();
        }
    }

}
