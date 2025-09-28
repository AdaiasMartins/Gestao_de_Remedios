package ufpb.dsc.gestao_de_remedios.Medicine.Mappers;

import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineCreateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineResponseDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.DTOs.MedicineUpdateDTO;
import ufpb.dsc.gestao_de_remedios.Medicine.Models.Medicine;

public class MedicineMapper {

    public static Medicine toEntity(MedicineCreateDTO dto) {
        var m = new Medicine();
        m.setName(dto.name());
        m.setCategory(dto.category());
        m.setManufacturer(dto.manufacturer());
        m.setRxRequired(dto.rxRequired());
        m.setPrice(dto.price());
        return m;
    }

    public static void updateEntity(Medicine m, MedicineUpdateDTO dto) {
        m.setName(dto.name());
        m.setCategory(dto.category());
        m.setManufacturer(dto.manufacturer());
        m.setRxRequired(dto.rxRequired());
        m.setPrice(dto.price());
    }

    public static MedicineResponseDTO toDto(Medicine m) {
        return new MedicineResponseDTO(
                m.getId(), m.getName(), m.getCategory(),
                m.getManufacturer(), m.getRxRequired(), m.getPrice()
        );
    }
}
