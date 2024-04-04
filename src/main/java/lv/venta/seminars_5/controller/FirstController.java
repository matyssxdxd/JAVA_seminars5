package lv.venta.seminars_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstController {

    @GetMapping("/hello/{name}")
    public String getHello(@PathVariable("name") String name) {
        System.out.println("Hello");
        return "hello-page";
    }

}
