package pe.edu.idat.week_6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.idat.week_6.model.Product;

import java.util.Optional;

public interface ProductService {

    void add(Product product);

    void edit(Product product);

    void delete(Long id);

    Optional<Product> get(Long id);

    Page<Product> all(Pageable pageable);

}
