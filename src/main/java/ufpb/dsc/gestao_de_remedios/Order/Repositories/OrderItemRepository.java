package ufpb.dsc.gestao_de_remedios.Order.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufpb.dsc.gestao_de_remedios.Order.Models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
