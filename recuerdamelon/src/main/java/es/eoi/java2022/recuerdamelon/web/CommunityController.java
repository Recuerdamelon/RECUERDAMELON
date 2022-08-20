package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.Optional;

@Controller
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;
    private final CommunityRepository communityRepository;

    public CommunityController(CommunityService communityService, UserService userService, CommunityRepository communityRepository) {
        this.communityService = communityService;
        this.userService = userService;
        this.communityRepository = communityRepository;
    }

    //********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...

    @GetMapping("/community/list")               //    /ADMIN
   // @PostAuthorize("hasRole('ROLE_ADMIN') or #model[community].user_id == authentication.principal.id")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            model.addAttribute("communities", userService.findCommunitiesByUserId(user.getId()));
            return "community/list";
    }

    @GetMapping("/community/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("users", communityService.findFriends(id));
        return "community/detail";
    }

    @GetMapping("/community/create")
    public String create(ModelMap model) {
        final CommunityDTO dto = new CommunityDTO();
        model.addAttribute("newCommunity", dto);
        return "community/create";
    }

    @Transactional
    @PostMapping(value = { "/community/{id}/edit", "/community/create" })
    public String save(CommunityDTO dto) {
//        Community community = new Community();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        community.addUser(userService.findById(user.getId()));
        dto.setUser(userService.findById(user.getId()));
        dto.setAdmin(true);
        this.communityRepository.save(communityService.save(dto));
        return "redirect:/community";
    }



}
