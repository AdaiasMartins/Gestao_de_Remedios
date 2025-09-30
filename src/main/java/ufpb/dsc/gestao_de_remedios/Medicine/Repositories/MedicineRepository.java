package ufpb.dsc.gestao_de_remedios.Medicine.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ufpb.dsc.gestao_de_remedios.Medicine.Models.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Page<Medicine> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
