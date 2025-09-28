package ufpb.dsc.gestao_de_remedios.Medicine.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineCreateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineResponseDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineUpdateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.Services.MedicineService;

import java.util.List;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicineResponseDTO create(@RequestBody @Valid MedicineCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<MedicineResponseDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public MedicineResponseDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public MedicineResponseDTO update(@PathVariable Long id, @RequestBody @Valid MedicineUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
