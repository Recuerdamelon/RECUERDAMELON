package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public List<User> findAll(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        return userService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") Integer id) { //Optional? DUDA 2
        return Optional.ofNullable(userService.findById(id));
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        userService.deleteById(id);
    }

    @PostMapping("/user")
    public UserDTO save(UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/user")
    public User update(User user) {
        return userService.update(user);
    }
}

