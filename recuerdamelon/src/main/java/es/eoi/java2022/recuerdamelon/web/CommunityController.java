package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.dto.CommunityUserCreationDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
import es.eoi.java2022.recuerdamelon.service.MensajesService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import es.eoi.java2022.recuerdamelon.service.mapper.CommunityServiceMapper;
import es.eoi.java2022.recuerdamelon.service.mapper.MensajesServiceMapper;
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
import java.util.*;

@Controller
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;
    private final CommunityRepository communityRepository;

    private  final CommunityServiceMapper communityServiceMapper;
    private final MensajesService mensajesService;
    private final MensajesServiceMapper mapper;

    public CommunityController(CommunityService communityService, UserService userService, CommunityRepository communityRepository, CommunityServiceMapper communityServiceMapper, MensajesService mensajesService, MensajesServiceMapper mapper) {
        this.communityService = communityService;
        this.userService = userService;
        this.communityRepository = communityRepository;
        this.communityServiceMapper = communityServiceMapper;
        this.mensajesService = mensajesService;
        this.mapper = mapper;
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
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("users", communityService.findFriends(id));
        model.addAttribute("community", communityService.findById(id));
        model.addAttribute("user", user);
        return "community/detail";
    }

    @GetMapping("/community/create")
    public String create(ModelMap model) {
        final CommunityDTO dto = new CommunityDTO();
        model.addAttribute("newCommunity", dto);
        return "community/create";
    }

    @Transactional
    @PostMapping("/community/create")
    public String save(CommunityDTO dto, Errors errors, RedirectAttributes redirectAttributes) {
        List<Community> communities = communityService.findAll(Pageable.unpaged());
        List<String> names = new ArrayList<>();
        for (Community com : communities) {
            names.add(com.getName());
        }
        if (dto.getName() == "") {
            redirectAttributes.addFlashAttribute("errorCreatingCommunity", true);
            redirectAttributes.addFlashAttribute("errorCreatingCommunity1", true);
            return "redirect:/community/create";
        } else if (names.contains(dto.getName())) {
            redirectAttributes.addFlashAttribute("errorNamingCommunity", true);
            redirectAttributes.addFlashAttribute("errorNamingCommunity1", true);
            return "redirect:/community/create";
        } else {
            final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//            Set<User> Friends = new HashSet<>();
//            Friends.add(userService.findById(user.getId()));
//            dto.setUsers(Friends);
            dto.setAdmin(user.getId());
            Set<User> administrador = new HashSet<>();
            administrador.add(user);
            dto.setUsers(administrador);
            this.communityRepository.save(communityService.save(dto));
            return "redirect:/community/list";
        }
    }

    @GetMapping("/community/{name}/users")
    public String inputUsers(@PathVariable("name") String name, ModelMap model) {
        Community toAdd = communityService.findByName(name);
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
        for (User userExists : all) {
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
        if (toAdd.size()!=0) {
            List<User> recieveInvitation = new ArrayList<>();
            for (String invited : toAdd) {
                recieveInvitation.add(userService.findByUsername(invited));
            }
            Invitation.makeInvitation(toAdd, name, userAdmin, recieveInvitation, userService, mensajesService);
        }else{
            redirectAttributes.addFlashAttribute("erroraddusername", true);
            return "redirect:/community/{name}/users";
        }
//        return "Ha ocurrido un error";

        return "redirect:/community/list";
    }

    @GetMapping("/community/{name}/delete")
    public String deleted(@PathVariable("name") String name, ModelMap model) {
        Community community = communityService.findByName(name);
        List<User> membersIn = communityService.findFriends(community.getId());
        Mensajes deleted = new Mensajes();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        deleted.setDate(DateUtil.dateToString(DateUtil.timeStamp()));
        Set<User> members = new HashSet<>();
        for (User member:membersIn) {
            members.add(member);
            Set<User> one = new HashSet<>();
            one.add(member);
            deleted.setReciever(member.getId());
            deleted.setUsers(one);
            deleted.setTitle("Grupo " + name + " eliminado");
            deleted.setMensaje("El usuario administrador del grupo " + name + " ha eliminado el grupo");
            deleted.setRecieved(true);
            mensajesService.save(mapper.toDto(deleted));
        }

        this.communityService.delete(community);
        return "redirect:/community/list";
    }

    @GetMapping("/community/{name}/{username}/delete")
    public String deleteUser(@PathVariable("name") String name,@PathVariable("username") String username, ModelMap model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Community community = communityService.findByName(name);
        User toDelete = userService.findByUsername(username);
        Mensajes deleted = new Mensajes();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        deleted.setDate(DateUtil.dateToString(DateUtil.timeStamp()));
        Set<User> members = new HashSet<>();
        members.add(toDelete);
        members.add(user);
        for (User member:members) {
            Set<User> one = new HashSet<>();
            one.add(member);
            deleted.setReciever(member.getId());
            deleted.setUsers(one);
            deleted.setTitle("Grupo " + name + " eliminado");
            deleted.setMensaje("El usuario administrador del grupo " + name + " te ha eliminado del grupo");
            deleted.setRecieved(true);
            mensajesService.save(mapper.toDto(deleted));
        }
        Set<User> newComm = new HashSet<>(community.getUsers());
        newComm.remove(toDelete);
        community.setUsers(newComm);
        this.communityRepository.save(community);
        return "redirect:/community/list";
    }
}
