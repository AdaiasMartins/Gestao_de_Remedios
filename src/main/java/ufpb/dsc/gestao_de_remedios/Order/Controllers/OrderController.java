package ufpb.dsc.gestao_de_remedios.Order.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufpb.dsc.gestao_de_remedios.Order.DTOs.*;
import ufpb.dsc.gestao_de_remedios.Order.Services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO create(@RequestBody @Valid OrderCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<OrderResponseDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO update(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
