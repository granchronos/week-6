package pe.edu.idat.week_6.service;

import pe.edu.idat.week_6.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    void add(Customer customer);

    List<Customer> all();

    Optional<Customer> get(Long id);

    void edit(Customer customer);

    void delete(Long id);

}
