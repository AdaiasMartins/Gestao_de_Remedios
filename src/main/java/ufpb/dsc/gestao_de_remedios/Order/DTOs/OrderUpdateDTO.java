package ufpb.dsc.gestao_de_remedios.Order.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record OrderUpdateDTO(
        @NotNull Long customerId,
        @NotNull @Size(min = 1) List<OrderItemCreateDTO> items
) {}
