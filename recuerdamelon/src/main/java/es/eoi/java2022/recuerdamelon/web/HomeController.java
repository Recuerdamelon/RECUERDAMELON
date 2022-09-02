package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/conocenos")
    public String conocenos()  { return "conocenos"; }
    
    @GetMapping("/perfil")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[user].userId == authentication.principal.id") //PRE
    public String perfil(Model model, @AuthenticationPrincipal User user)
    {
        model.addAttribute("userData", user);
        return "perfil";
    }
}
