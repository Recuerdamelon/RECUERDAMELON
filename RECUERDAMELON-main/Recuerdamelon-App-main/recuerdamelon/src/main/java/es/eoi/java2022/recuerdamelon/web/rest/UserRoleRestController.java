package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.UserRoleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userrole")
public class UserRoleRestController {


    private final UserRoleService userRoleService;

    public UserRoleRestController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/userrole")
    public List<UserRole> findAll(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        return userRoleService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping("/userrole/{id}")
    public Optional<UserRole> findById(@PathVariable(value = "id") Integer id) {
        return Optional.ofNullable(userRoleService.findById(id));
    }

    @DeleteMapping("/userrole/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        userRoleService.deleteById(id);
    }

    @PostMapping("/userrole")
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        return userRoleService.save(userRoleDTO);
    }

    @PutMapping("/userrole")
    public UserRole update(UserRole userRole) {
        return userRoleService.update(userRole);
    }
}

