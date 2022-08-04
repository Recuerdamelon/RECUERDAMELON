package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
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
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    //********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/community")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", communityService.findAll(pageable));
        return "community/list";
    }

    @GetMapping("/community/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN')") //Just Admin, users could know it via User info
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("community", this.communityService.findById(id));
        return "community/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/community/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN')") //Just Admin
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("community", this.communityService.findById(id));
        return "community/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/community/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")//PRE // Just Admin
    public String save(CommunityDTO dto) {
        return String.format("redirect:/communities/%s",
                this.communityService.save(dto).getId());
    }

    //# DELETE
    @PostMapping("/community/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //PRE //Just Admin
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.communityService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus");//url del view...

//                    //Añadimos los atributos de la sesión
//                    .addObject("entityId", id)
//                    .addObject("entityName", "userrole")
//                    //Añadimos un registro de la excepción como atributo
//                    .addObject("errorCause", e.getRootCause().getMessage())
//                    //Y añadimos atributo link para volver a userrole
//                    .addObject("backLink", "/userrole");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/community";//...redirigimos a "/userrole"
    }


}
