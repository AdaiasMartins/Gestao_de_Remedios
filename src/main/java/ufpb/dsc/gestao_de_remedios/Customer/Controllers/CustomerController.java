package ufpb.dsc.gestao_de_remedios.Customer.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufpb.dsc.gestao_de_remedios.Customer.DTOs.*;
import ufpb.dsc.gestao_de_remedios.Customer.Services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO create(@RequestBody @Valid CustomerCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CustomerResponseDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable Long id, @RequestBody @Valid CustomerUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
