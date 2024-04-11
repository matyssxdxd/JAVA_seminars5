package lv.venta.seminars_5.controller;

import jakarta.validation.Valid;
import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.service.ICRUDProductService;
import lv.venta.seminars_5.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Controller
public class FirstController {

    @Autowired
    private ICRUDProductService crudService;

    @Autowired
    private IFilterProductService filterService;

    @GetMapping("/hello")
    public String getHello(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "surname", required = false) String surname, Model model) {
        if (Objects.equals(name.toLowerCase(), "daniels")) name = "Slinkis";
        if (surname != null) {
            if (Objects.equals(surname.toLowerCase(), "kalnavs")) surname = "Kalmars";
        }
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "hello-page";
    }

    @GetMapping("/hello/{name}")
    public String getHelloName(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("msg", "Wrong id!");
        return "error-page";
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            try {
                model.addAttribute("products", crudService.retrieveAll());
                return "product-page";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                return "error-page";
            }
        }

        try {
            model.addAttribute("products", crudService.retrieveById(id));
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @GetMapping("/product/test")
    public String getProductTest(Model model) {
        try {
            model.addAttribute("products", crudService.retrieveAll().get(0));
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/product/insert")
    public String getProductInsert(Model model) {
        model.addAttribute("product", new Product());
        return "insert-product-page";
    }

    @PostMapping("/product/insert")
    public String postProductInsert(@Valid Product product, BindingResult result) {

        if (result.hasErrors()) return "insert-product-page";

        try {
            Product newProduct = crudService.createProduct(product);
            return "redirect:/product";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/error")
    public String getError(Model model) {
        model.addAttribute("msg", "Some error");
        return "error-page";
    }

}
