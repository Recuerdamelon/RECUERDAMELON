package es.eoi.java2022.recuerdamelon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }
}
