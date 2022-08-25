package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.ConfirmationToken;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.ConfirmationTokenRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.dto.service.EmailService;
import es.eoi.java2022.recuerdamelon.dto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

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


//    @PostConstruct
//    public void init(){
//        System.out.println("HOla mundo");
//    }

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
        User user = userService.findByEmail(email);
        model.addAttribute("userDoesNotExist", false);
        if (user == null) {
            model.addAttribute("userDoesNotExist", true);
            return "emailSentNewPassword";
        }
        // Create token
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        // Save it
        confirmationTokenRepository.save(confirmationToken);
        emailService.sendSimpleMessage(email, "Recuperar Contraseña", "Haz click en el siguiente enlace para cambiar tu contraseña. " + "http://localhost:8080/enterNewPassword?token=" + confirmationToken.getConfirmationToken()); // FUNCIONA ENVÍA EL EMAIL SI ESTÁ REGISTRADO DEFINITIVO
        return "emailSentNewPassword";
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
    public String getRegistro(WebRequest request, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registro";
    }

    @Transactional
    @PostMapping("/registro")
    public String saveRegistro(UserDTO userDTO, Model model, Errors errors, RedirectAttributes redirectAttributes) {
        List<User> allUsers = userService.findAll(Pageable.unpaged());
        if (allUsers.contains(userService.findByUsername(userDTO.getUsername()))) {
            redirectAttributes.addFlashAttribute("errorusername", true);
            return "redirect:/registro";
        } else if (allUsers.contains(userService.findByEmail(userDTO.getEmail()))) {
            redirectAttributes.addFlashAttribute("erroremail", true);
            return "redirect:/registro";
        } else {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userDTO.setActive(true);
            this.userRepository.save(userService.save(userDTO));
            return "login";
        }

    }

    @GetMapping("/registrobusiness")
    public String getRegistrobusiness(WebRequest request, Model model) {
       UserDTO userDTO = new UserDTO();
        model.addAttribute("business", userDTO);
        return "BusinessRegistro";
    }

    @Transactional
    @PostMapping("/registrobusiness")
    public String saveRegistrobusiness(UserDTO userDTO, Model model, Errors errors, RedirectAttributes redirectAttributes) {
        List<User> allbusiness = userService.findAll(Pageable.unpaged());
        if (allbusiness.contains(userService.findByUsername(userDTO.getName()))) {
            redirectAttributes.addFlashAttribute("errorusername", true);
            return "redirect:/BusinessRegistro";
        } else if (allbusiness.contains(userService.findByEmail(userDTO.getEmail()))) {
            redirectAttributes.addFlashAttribute("erroremail", true);
            return "redirect:/BusinessRegistro";
        } else {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userDTO.setActive(true);
            userDTO.setBusiness(true);
            userDTO.setName("business");
            userDTO.setSurname("business");
            this.userRepository.save(userService.save(userDTO));
            return "login";
        }

    }
}



