package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.dto.service.UserService;
import es.eoi.java2022.recuerdamelon.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Optional;
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/user")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", userService.findAll(pageable));
        return "user/list";
    }

    @GetMapping("/user/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[user].userId == authentication.principal.id")
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("user", this.userService.findById(id));
        return "user/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/user/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[user].userId == authentication.principal.id")
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("user", this.userService.findById(id));
        return "user/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/user/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[user].userId == authentication.principal.id")//PRE
    public String save(UserDTO dto) {
        return String.format("redirect:/users/%s",
                this.userService.save(dto).getId());
    }

    //# DELETE
    @PostMapping("/user/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[user].userId == authentication.principal.id") //PRE
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.userService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus")//url del view...

                    //Añadimos los atributos de la sesión
                    .addObject("entityId", id)
                    .addObject("entityName", "user")
                    //Añadimos un registro de la excepción como atributo
                    .addObject("errorCause", e.getRootCause().getMessage())
                    //Y añadimos atributo link para volver a user
                    .addObject("backLink", "/user");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/user";//...redirigimos a "/user"
    }

        //SAVE USER PROFILE IMAGE
        @Autowired
        private UserRepository repo;
        @PostMapping("/user/save")
        public RedirectView saveUser(@AuthenticationPrincipal User user, @RequestParam("image") MultipartFile multipartFile) throws IOException {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setAvatar(fileName);

            repo.save(user);

             String uploadDir = "user-photos/" + user.getId();

              FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            return new RedirectView("/perfil", true);
        }

}


