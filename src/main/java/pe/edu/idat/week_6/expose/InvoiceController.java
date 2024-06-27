package pe.edu.idat.week_6.expose;

import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.model.Invoice;
import pe.edu.idat.week_6.model.InvoiceDetail;
import pe.edu.idat.week_6.model.Product;
import pe.edu.idat.week_6.service.CustomerService;
import pe.edu.idat.week_6.service.InvoiceService;
import pe.edu.idat.week_6.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final CustomerService customerService;

    private final ProductService productService;

    @GetMapping("/register")
    public String showForm(Model model, Pageable pageable) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("customers", customerService.all());
        model.addAttribute("products", productService.all(pageable));
        return "invoices/register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute Invoice invoice, @RequestParam Map<String, String> requestParams, Pageable pageable) {
        List<InvoiceDetail> details = new ArrayList<>();
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (entry.getKey().startsWith("product_")) {
                Long id = Long.parseLong(entry.getKey().substring(9));
                int quantity = Integer.parseInt(entry.getValue());
                if (quantity > 0) {
                    Product product = productService.get(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
                    InvoiceDetail detail = new InvoiceDetail();
                    detail.setProduct(product);
                    detail.setCount(quantity);
                    detail.setInvoice(invoice);
                    details.add(detail);
                }
            }
        }
        invoice.setCustomer(customerService.get(invoice.getCustomer().getId()).orElseThrow());
        invoice.setAmount(details.stream().mapToDouble(detail -> detail.getProduct().getPrice() * detail.getCount()).sum());
        invoice.setDetails(details);
        invoiceService.add(invoice);
        model.addAttribute("invoices", invoiceService.all(pageable));
        return "redirect:/invoice/list";
    }

    @GetMapping("/list")
    public String list(Model model, Pageable pageable) {
        model.addAttribute("page", invoiceService.all(pageable));
        return "invoices/list";
    }

}
