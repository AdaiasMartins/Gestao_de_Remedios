package ufpb.dsc.gestao_de_remedios.Medicine.DTOs;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record MedicineUpdateDTO(
        @NotBlank String name,
        String category,
        String manufacturer,
        @NotNull Boolean rxRequired,
        @NotNull @DecimalMin("0.01") BigDecimal price
) {}
