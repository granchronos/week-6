package pe.edu.idat.week_6.expose;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.week_6.model.Product;
import pe.edu.idat.week_6.service.ProductService;

@Controller
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/register";
    }

    @PostMapping("register")
    public String registerForm(Model model, @Valid @ModelAttribute Product product,
                               BindingResult result, Pageable pageable) {
        if (result.hasErrors()) {
            return "products/register";
        }
        productService.add(product);
        model.addAttribute("products", this.productService.all(pageable));
        return "redirect:/product/list";
    }

    @GetMapping("list")
    public String showList(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("productPage", productService.all(pageable));
        return "products/list";
    }

    @GetMapping("edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        productService.get(id)
                .ifPresent(product -> model.addAttribute("product", product));
        return "products/edit";
    }

    @PostMapping("edit")
    public String editForm(Model model, @Valid @ModelAttribute Product product,
                           BindingResult result, Pageable pageable) {
        if (result.hasErrors()) {
            return "products/edit";
        }
        productService.edit(product);
        model.addAttribute("products", this.productService.all(pageable));
        return "redirect:/product/list";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product/list";
    }

}
