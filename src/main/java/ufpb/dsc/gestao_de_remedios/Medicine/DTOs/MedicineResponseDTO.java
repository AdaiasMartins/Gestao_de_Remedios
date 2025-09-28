package ufpb.dsc.gestao_de_remedios.Medicine.DTOs;

import java.math.BigDecimal;

public record MedicineResponseDTO(
        Long id,
        String name,
        String category,
        String manufacturer,
        Boolean rxRequired,
        BigDecimal price
) {}
