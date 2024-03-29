package ma.darrazi.customerservice.web;

import lombok.RequiredArgsConstructor;
import ma.darrazi.customerservice.entities.Customer;
import ma.darrazi.customerservice.repository.CustomerRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }
    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerRepository.findById(id).get();
    }

}
