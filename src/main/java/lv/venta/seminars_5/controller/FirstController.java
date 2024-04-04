package lv.venta.seminars_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
