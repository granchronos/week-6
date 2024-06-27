package pe.edu.idat.week_6.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.repository.CustomerRepository;
import pe.edu.idat.week_6.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> all() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> get(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void edit(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
