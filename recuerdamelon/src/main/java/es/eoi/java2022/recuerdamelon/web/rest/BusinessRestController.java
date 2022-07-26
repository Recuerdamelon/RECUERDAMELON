package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Business;
import es.eoi.java2022.recuerdamelon.dto.BusinessDTO;
import es.eoi.java2022.recuerdamelon.service.BusinessService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/business")
public class BusinessRestController {
    private final BusinessService businessService;

    public BusinessRestController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/business")
    public List<Business> findAll(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        return businessService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping("/business/{id}")
    public Optional<Business> findById(@PathVariable(value = "id") Integer id) { //Optional? DUDA 2
        return Optional.ofNullable(businessService.findById(id));
    }

    @DeleteMapping("/business/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {businessService.deleteById(id);}

    @PostMapping("/business")
    public BusinessDTO save(BusinessDTO businessDTO) {return businessService.save(businessDTO);}

    @PutMapping("/business")
    public Business update(Business business) {return businessService.update(business);}
}
