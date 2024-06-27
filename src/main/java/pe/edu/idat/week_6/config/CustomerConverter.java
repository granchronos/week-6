package pe.edu.idat.week_6.config;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.service.CustomerService;

@Component
@AllArgsConstructor
public class CustomerConverter implements Converter<String, Customer> {

    private final CustomerService customerService;

    @Override
    public Customer convert(String source) {
        return customerService.get(Long.parseLong(source)).orElseThrow();
    }
}
