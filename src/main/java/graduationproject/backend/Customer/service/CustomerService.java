package graduationproject.backend.Customer.service;

import graduationproject.backend.Customer.entity.Customer;
import graduationproject.backend.Customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepo;
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }
}
