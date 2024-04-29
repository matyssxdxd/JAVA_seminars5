package lv.venta.seminars_5.controller;

import jakarta.validation.Valid;
import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.service.ICRUDProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ICRUDProductService crudService;

    @GetMapping("")
    public String getProduct(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            try {
                model.addAttribute("products", crudService.retrieveAll());
                model.addAttribute("title", "All products");
                return "product-page";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                return "error-page";
            }
        }

        try {
            model.addAttribute("products", crudService.retrieveById(id));
            model.addAttribute("title", "Product by ID");
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @GetMapping("/test")
    public String getProductTest(Model model) {
        try {
            model.addAttribute("products", crudService.retrieveAll().get(0));
            model.addAttribute("title", "Product test");
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/insert")
    public String getProductInsert(Model model) {
        model.addAttribute("product", new Product());
        return "insert-product-page";
    }

    @PostMapping("/insert")
    public String postProductInsert(@Valid Product product, BindingResult result) {

        if (result.hasErrors()) return "insert-product-page";

        try {
            crudService.createProduct(product);
            return "redirect:/product";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/update")
    public String getProductUpdate(@RequestParam(value = "id") Integer id, Model model) {
        try {
            model.addAttribute("product", crudService.retrieveById(id));
            model.addAttribute("id", id);
            return "update-product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String postProductUpdate(@RequestParam(value ="id") Integer id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-product-page";
        }

        try {
            crudService.updateById(id, product);
            return "redirect:/product";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/delete")
    public String getProductDelete(@RequestParam(value = "id") Integer id, Model model) {
        try {
            crudService.deleteById(id);
            return "redirect:/product";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }
}
