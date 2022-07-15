package es.eoi.java2022.recuerdamelon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class LoginController {

    @PostConstruct
    public void init(){
        System.out.println("HOla mundo");
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errors", true);
        return "login";
    }
}