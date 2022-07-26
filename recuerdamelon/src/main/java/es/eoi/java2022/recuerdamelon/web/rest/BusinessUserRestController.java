package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.BusinessUser;
import es.eoi.java2022.recuerdamelon.dto.BusinessUserDTO;
import es.eoi.java2022.recuerdamelon.service.BusinessUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/businessuser")
public class BusinessUserRestController {
    private final BusinessUserService businessUserServiceService;

    public BusinessUserRestController(BusinessUserService businessUserServiceService) {
        this.businessUserServiceService = businessUserServiceService;
    }


    @GetMapping("/businessuser")
    public List<BusinessUser> findAll(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        return businessUserServiceService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping("/businessuser/{id}")
    public Optional<BusinessUser> findById(@PathVariable(value = "id") Integer id) { //Optional? DUDA 2
        return Optional.ofNullable(businessUserServiceService.findById(id));
    }

    @DeleteMapping("/businessuser/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {businessUserServiceService.deleteById(id);}

    @PostMapping("/businessuser")
    public BusinessUserDTO save(BusinessUserDTO businessUserDTO) {return businessUserServiceService.save(businessUserDTO);}

    @PutMapping("/businessuser")
    public BusinessUser update(BusinessUser businessUser) {return businessUserServiceService.update(businessUser);}
}
