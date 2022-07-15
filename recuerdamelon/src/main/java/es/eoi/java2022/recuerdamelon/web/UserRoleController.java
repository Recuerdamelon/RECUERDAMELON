package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.UserRoleService;
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
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/userrole")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", userRoleService.findAll(pageable));
        return "userrole/list";
    }

    @GetMapping("/userrole/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN')") //Just Admin, users could know it via User info
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("userrole", this.userRoleService.findById(id));
        return "userrole/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/userrole/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN')") //Just Admin
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("userrole", this.userRoleService.findById(id));
        return "userrole/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/userrole/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")//PRE // Just Admin
    public String save(UserRoleDTO dto) {
        return String.format("redirect:/userroles/%s",
                this.userRoleService.save(dto).getId());
    }

    //# DELETE
    @PostMapping("/userrole/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //PRE //Just Admin
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.userRoleService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus")//url del view...

                    //Añadimos los atributos de la sesión
                    .addObject("entityId", id)
                    .addObject("entityName", "userrole")
                    //Añadimos un registro de la excepción como atributo
                    .addObject("errorCause", e.getRootCause().getMessage())
                    //Y añadimos atributo link para volver a userrole
                    .addObject("backLink", "/userrole");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/userrole";//...redirigimos a "/userrole"
    }


}
