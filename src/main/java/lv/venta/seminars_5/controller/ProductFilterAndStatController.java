package lv.venta.seminars_5.controller;

import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/product/info")
public class ProductFilterAndStatController {

    @Autowired
    private IFilterProductService filterService;

    @GetMapping("/filter/price")
    public String getProductFilterPrice(@RequestParam("limit") float limit, Model model)  {
        try {
            ArrayList<Product> products = filterService.filterProduct(limit);
            model.addAttribute("products", products);
            model.addAttribute("title", "Products by price");
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/filter/quantity")
    public String getProductFilterQuantity(@RequestParam("threshold") int threshold, Model model) {
        try {
            ArrayList<Product> products = filterService.filterProductByQuantityThreshold(threshold);
            model.addAttribute("products", products);
            model.addAttribute("title", "Products by quantity");
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/filter/keyword")
    public String getProductFilterKeyword(@RequestParam("word") String word, Model model) {
        try {
            ArrayList<Product> products = filterService.filterByTitleOrDescription(word);
            model.addAttribute("products", products);
            model.addAttribute("title", "Products by keyword");
            return "product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/total")
    public String getProductTotal(Model model) {
        try {
            float value = filterService.calculateTotalProductValue();
            model.addAttribute("total", Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2));
            return "product-total";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/error";
        }
    }
}
