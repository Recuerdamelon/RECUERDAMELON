package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.dto.BusinessUserDTO;
import es.eoi.java2022.recuerdamelon.service.BusinessUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BusinessUserController {

    private final BusinessUserService businessUserService;

    public BusinessUserController(BusinessUserService businessUserService) {this.businessUserService = businessUserService;}

    //********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/businessUser")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", businessUserService.findAll(pageable));
        return "businessUser/list";
    }

    @GetMapping("/businessUser/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[businessUser].userId == authentication.principal.id")
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("user", this.businessUserService.findById(id));
        return "businessUser/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/businessUser/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[businessUser].userId == authentication.principal.id")
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("businessUser", this.businessUserService.findById(id));
        return "businessUser/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/businessUser/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[businessUser].userId == authentication.principal.id")//PRE
    public String save(BusinessUserDTO dto) {
        return String.format("redirect:/businessUser/%s",
                this.businessUserService.save(dto).getIdTrabajador());
    }

    //# DELETE
    @PostMapping("/businessUser/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[businessUser].userId == authentication.principal.id") //PRE
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.businessUserService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus")//url del view...

                    //Añadimos los atributos de la sesión
                    .addObject("entityId", id)
                    .addObject("entityName", "businessUser")
                    //Añadimos un registro de la excepción como atributo
                    .addObject("errorCause", e.getRootCause().getMessage())
                    //Y añadimos atributo link para volver a user
                    .addObject("backLink", "/businessUser");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/businessUser";//...redirigimos a "/user"
    }

}
