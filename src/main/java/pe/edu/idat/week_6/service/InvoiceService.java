package pe.edu.idat.week_6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.idat.week_6.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    void add(Invoice invoice);

    void edit(Invoice invoice);

    void delete(Long id);

    Optional<Invoice> get(Long id);

    Page<Invoice> all(Pageable pageable);

}
