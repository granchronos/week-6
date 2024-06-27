package pe.edu.idat.week_6.expose;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    @PostMapping("register")
    public String registerForm(Model model, @Valid @ModelAttribute Customer customer,
                               BindingResult result) {
        if (result.hasErrors()) {
            return"customer/register";
        }
        customerService.add(customer);
        model.addAttribute("customers", this.customerService.all());
        return "customer/list";
    }

    @GetMapping("list")
    public String showList(Model model) {
        model.addAttribute("customers", customerService.all());
        return "customer/list";
    }

    @GetMapping("edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        customerService.get(id)
                .ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer/edit";
    }

    @PostMapping("edit")
    public String editForm(Model model, @Valid @ModelAttribute Customer customer,
                               BindingResult result) {
        if (result.hasErrors()) {
            return"edit";
        }
        customerService.edit(customer);
        model.addAttribute("customers", this.customerService.all());
        return "customer/list";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        customerService.delete(id);
        return "customer/list";
    }

}
