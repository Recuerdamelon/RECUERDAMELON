package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.dto.CommunityUserCreationDTO;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
import es.eoi.java2022.recuerdamelon.service.MensajesService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import es.eoi.java2022.recuerdamelon.utils.Invitation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Controller
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;
    private final CommunityRepository communityRepository;

    private final MensajesService mensajesService;

    public CommunityController(CommunityService communityService, UserService userService, CommunityRepository communityRepository, MensajesService mensajesService) {
        this.communityService = communityService;
        this.userService = userService;
        this.communityRepository = communityRepository;
        this.mensajesService = mensajesService;
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
        model.addAttribute("community", communityService.findById(id));
        return "community/detail";
    }

    @GetMapping("/community/create")
    public String create(ModelMap model) {
        final CommunityDTO dto = new CommunityDTO();
        model.addAttribute("newCommunity", dto);
        return "community/create";
    }

    @Transactional
    @PostMapping(value = {"/community/{id}/edit", "/community/create"})
    public String save(CommunityDTO dto, Errors errors, RedirectAttributes redirectAttributes) {
        List<Community> communities = communityService.findAll(Pageable.unpaged());
        List<String> names = new ArrayList<>();
        for (Community com : communities) {
            names.add(com.getName());
        }
        if (dto.getName() == "") {
            redirectAttributes.addFlashAttribute("errorCreatingCommunity", true);
            return "redirect:/community/create";
        } else if (names.contains(dto.getName())) {
            redirectAttributes.addFlashAttribute("errorNamingCommunity", true);
            return "redirect:/community/create";
        } else {
            final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Set<User> Friends = new HashSet<>();
            Friends.add(userService.findById(user.getId()));
            dto.setUsers(Friends);
            dto.setAdmin(user.getId());

            this.communityRepository.save(communityService.save(dto));
            return "redirect:/community/list";
        }
    }

    @GetMapping("/community/{name}/users")
    public String inputUsers(@PathVariable("name") String name, ModelMap model) {
        Community toAdd = communityService.findByName(name);
        Set<User> Friends = new HashSet<>();
        CommunityUserCreationDTO creationDTO = new CommunityUserCreationDTO();
        model.addAttribute("addUsers", creationDTO);
        model.addAttribute("community", toAdd);
        return "/community/users";
    }

    @Transactional
    @PostMapping(value = {"/community/{name}/usersAdded"})
    public String addUsers(CommunityUserCreationDTO dto, @PathVariable("name") String name,
                           Errors errors, RedirectAttributes redirectAttributes) {

        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userAdmin = userService.findById(user.getId());
        List<User> all = userService.findAll(Pageable.unpaged());
        List<String> allUsernames = new ArrayList<>();
        for (User userExists:all) {
            allUsernames.add(userExists.getUsername());
        }

        List<String> toAdd = new ArrayList<>();
        if (dto.getUser1() != null && allUsernames.contains(dto.getUser1())) {
            toAdd.add(dto.getUser1());
        }
        if (dto.getUser2() != null && allUsernames.contains(dto.getUser2())) {
            toAdd.add(dto.getUser2());
        }
        if (dto.getUser3() != null && allUsernames.contains(dto.getUser3())) {
            toAdd.add(dto.getUser3());
        }
        if (dto.getUser4() != null && allUsernames.contains(dto.getUser4())) {
            toAdd.add(dto.getUser4());
        }
        if (dto.getUser5() != null && allUsernames.contains(dto.getUser5())) {
            toAdd.add(dto.getUser5());
        }
        System.out.println("toAdd.size() = " + toAdd.size());
        System.out.println(allUsernames.size());
        if (allUsernames.contains(toAdd) && !toAdd.isEmpty()) {
            List<User> recieveInvitation = new ArrayList<>();
            for (String invited : toAdd) {
                recieveInvitation.add(userService.findByUsername(invited));
                mensajesService.save(Invitation.makeInvitation(toAdd, name, userAdmin, recieveInvitation));
            }
        }else{
            redirectAttributes.addFlashAttribute("erroraddusername", true);
            return "/community/users";
        }

        return "redirect:/community/list";

    }
}
