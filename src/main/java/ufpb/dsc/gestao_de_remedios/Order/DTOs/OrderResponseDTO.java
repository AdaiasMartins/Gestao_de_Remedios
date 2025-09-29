package ufpb.dsc.gestao_de_remedios.Order.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long customerId,
        Long userId,
        BigDecimal total,
        LocalDateTime createdAt,
        List<OrderItemResponseDTO> items
) {}
