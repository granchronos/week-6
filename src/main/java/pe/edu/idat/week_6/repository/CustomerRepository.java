package pe.edu.idat.week_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.week_6.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}