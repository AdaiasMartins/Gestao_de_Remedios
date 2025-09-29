package ufpb.dsc.gestao_de_remedios.Order.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemCreateDTO(
        @NotNull Long medicineId,
        @NotNull @Min(1) Integer quantity
) {}
