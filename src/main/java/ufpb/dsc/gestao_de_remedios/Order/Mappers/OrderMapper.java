package ufpb.dsc.gestao_de_remedios.Order.Mappers;

import ufpb.dsc.gestao_de_remedios.Order.DTOs.OrderItemResponseDTO;
import ufpb.dsc.gestao_de_remedios.Order.DTOs.OrderResponseDTO;
import ufpb.dsc.gestao_de_remedios.Order.Models.Order;
import java.math.BigDecimal;

public class OrderMapper {

    public static OrderResponseDTO toDto(Order o) {
        var items = o.getItems().stream().map(i ->
                new OrderItemResponseDTO(
                        i.getMedicine().getId(),
                        i.getMedicine().getName(),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity()))
                )
        ).toList();
        return new OrderResponseDTO(
                o.getId(),
                o.getCustomer().getId(),
                o.getUser().getId(),
                o.getTotal(),
                o.getCreatedAt(),
                items
        );
    }
}
