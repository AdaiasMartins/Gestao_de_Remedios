package ufpb.dsc.gestao_de_remedios.Order.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record OrderCreateDTO(
        @NotNull
        Long customerId,
        @NotNull
        Long userId,
        @NotNull @Size(min = 1)
        List<OrderItemCreateDTO> items
) {}
