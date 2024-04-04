package lv.venta.seminars_5.controller;

import lv.venta.seminars_5.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Controller
public class FirstController {

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

    @GetMapping("/product")
    public String getProduct(Model model) {
        Product myProduct1 = new Product("Abols", "Sarkans", 1.23f, 5);
        Product myProduct2 = new Product("Bumbiers", "Zals", 0.99f, 12);
        Product myProduct3 = new Product("Koks", "Bruns", 200.3f, 9);
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(myProduct1, myProduct2, myProduct3));
        model.addAttribute("products", products);
        return "product-page";
    }

}
