package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.ConfirmationToken;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.ConfirmationTokenRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.service.EmailService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
public class LoginController {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
//        emailService.sendSimpleMessage("barbii.f26@gmail.com", "Prueba recuperacion", "Esta es una prueba de recuperacion"); // PARA PROBAR QUE ENVÍA, ¡¡BORRAR CUANDO HAYA REGISTROS
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("userDoesNotExist", true);
            return "emailSentNewPassword";
        }
        // Create token
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        // Save it
        confirmationTokenRepository.save(confirmationToken);
        emailService.sendSimpleMessage(email, "Recuperar Contraseña", "Haz click en el siguiente enlace para cambiar tu contraseña. " + "http://localhost:8080/recoverPassword?token="+confirmationToken.getConfirmationToken()); // FUNCIONA ENVÍA EL EMAIL SI ESTÁ REGISTRADO DEFINITIVO
        return "enterNewPassword";
    }

    @GetMapping("/enterNewPassword")
    public String getEnterNewPassword() {
        return "enterNewPassword";
    }
    // Endpoint to confirm the token
    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("emailId", user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Endpoint to update a user's password
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetUserPassword(ModelAndView modelAndView, User user) {
        if (user.getEmail() != null) {
            // Use email to find user
            User tokenUser = userRepository.findByEmailIgnoreCase(user.getEmail());
            tokenUser.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(tokenUser);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        } else {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @GetMapping("/registro")
    public String getRegistro() {
        return "registro";

    }
}