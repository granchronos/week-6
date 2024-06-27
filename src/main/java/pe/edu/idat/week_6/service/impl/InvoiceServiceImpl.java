package pe.edu.idat.week_6.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.idat.week_6.model.Invoice;
import pe.edu.idat.week_6.repository.InvoiceRepository;
import pe.edu.idat.week_6.service.InvoiceService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void add(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void edit(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Optional<Invoice> get(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Page<Invoice> all(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }
}
