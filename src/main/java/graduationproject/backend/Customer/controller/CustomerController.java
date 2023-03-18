package graduationproject.backend.Customer.controller;

import graduationproject.backend.Customer.dto.CustomerDTO;
import graduationproject.backend.Customer.entity.Customer;
import graduationproject.backend.Customer.mapper.CustomerMapper;
import graduationproject.backend.Customer.repository.CustomerRepository;
import graduationproject.backend.Customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @GetMapping
     ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO customer){
        Customer customerToSave = customerMapper.customerDTOToCustomer(customer);
        return new ResponseEntity<>(customerService.save(customerToSave), HttpStatus.OK);
    }


}