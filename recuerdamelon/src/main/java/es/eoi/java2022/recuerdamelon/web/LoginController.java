package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.service.EmailService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    EmailService emailService;

    @Resource
    UserService userService;

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

    @GetMapping("/recoverPassword")
    public String getRecoverPassword() {
        return "recoverPassword";

    }
    @GetMapping("/emailSentNewPassword")
    public String getEmailSentNewPassword() {
        return "emailSentNewPassword";
    }
    @PostMapping("/recoverPassword")
    public String postRecoverPassword(Model model, @RequestParam(name = "email") String email) {
        emailService.sendSimpleMessage("barbii.f26@gmail.com", "Prueba recuperacion", "Esta es una prueba de recuperacion"); // PARA PROBAR QUE ENVÍA, ¡¡BORRAR CUANDO HAYA REGISTROS
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("userDoesNotExist", true);
            return "emailSentNewPassword";
        }
        emailService.sendSimpleMessage(email, "Prueba recuperacion", "Esta es una prueba de recuperacion"); // FUNCIONA ENVÍA EL EMAIL SI ESTÁ REGISTRADO DEFINITIVO
        return "emailSentNewPassword";
    }

    @GetMapping("/enterNewPassword")
    public String getEnterNewPassword() {
        return "enterNewPassword";
    }

}