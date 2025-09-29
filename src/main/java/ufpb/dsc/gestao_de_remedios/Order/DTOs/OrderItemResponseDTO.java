package ufpb.dsc.gestao_de_remedios.Order.DTOs;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long medicineId,
        String medicineName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {}
