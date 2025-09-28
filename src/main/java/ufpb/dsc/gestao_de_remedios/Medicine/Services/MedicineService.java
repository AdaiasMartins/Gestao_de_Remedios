package ufpb.dsc.gestao_de_remedios.Medicine.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineCreateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineResponseDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineUpdateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.Mappers.MedicineMapper;
import ufpb.dsc.gestao_de_remedios.Medicine.Models.Medicine;
import ufpb.dsc.gestao_de_remedios.Medicine.Repositories.MedicineRepository;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MedicineResponseDTO create(MedicineCreateDTO dto) {
        Medicine saved = repository.save(MedicineMapper.toEntity(dto));
        return MedicineMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> list() {
        return repository.findAll().stream().map(MedicineMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public MedicineResponseDTO get(Long id) {
        Medicine m = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
        return MedicineMapper.toDto(m);
    }

    @Transactional
    public MedicineResponseDTO update(Long id, MedicineUpdateDTO dto) {
        Medicine m = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
        MedicineMapper.updateEntity(m, dto);
        Medicine updated = repository.save(m);
        return MedicineMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Medicine not found");
        repository.deleteById(id);
    }
}
