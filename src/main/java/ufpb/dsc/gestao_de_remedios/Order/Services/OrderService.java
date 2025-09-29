package ufpb.dsc.gestao_de_remedios.Order.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufpb.dsc.gestao_de_remedios.Customer.Repositories.CustomerRepository;
import ufpb.dsc.gestao_de_remedios.Medicine.Repositories.MedicineRepository;
import ufpb.dsc.gestao_de_remedios.Order.DTOs.*;
import ufpb.dsc.gestao_de_remedios.Order.Mappers.OrderMapper;
import ufpb.dsc.gestao_de_remedios.Order.Models.Order;
import ufpb.dsc.gestao_de_remedios.Order.Models.OrderItem;
import ufpb.dsc.gestao_de_remedios.Order.Repositories.OrderItemRepository;
import ufpb.dsc.gestao_de_remedios.Order.Repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final MedicineRepository medicineRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository itemRepository, CustomerRepository customerRepository, MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.medicineRepository = medicineRepository;
    }

    @Transactional
    public OrderResponseDTO create(OrderCreateDTO dto) {
        var customer = customerRepository.findById(dto.customerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        var order = new Order();
        order.setCustomer(customer);
        order.setTotal(BigDecimal.ZERO);
        order = orderRepository.save(order);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (var it : dto.items()) {
            var med = medicineRepository.findById(it.medicineId()).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
            var oi = new OrderItem();
            oi.setOrder(order);
            oi.setMedicine(med);
            oi.setQuantity(it.quantity());
            oi.setUnitPrice(med.getPrice());
            items.add(oi);
            total = total.add(med.getPrice().multiply(BigDecimal.valueOf(it.quantity())));
        }

        itemRepository.saveAll(items);
        order.setItems(items);
        order.setTotal(total);
        order = orderRepository.save(order);

        return OrderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> list() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO get(Long id) {
        var o = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return OrderMapper.toDto(o);
    }

    @Transactional
    public OrderResponseDTO update(Long id, OrderUpdateDTO dto) {
        var order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        var customer = customerRepository.findById(dto.customerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        order.setCustomer(customer);
        itemRepository.deleteAll(order.getItems());
        order.getItems().clear();

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();

        for (var it : dto.items()) {
            var med = medicineRepository.findById(it.medicineId()).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
            var oi = new OrderItem();
            oi.setOrder(order);
            oi.setMedicine(med);
            oi.setQuantity(it.quantity());
            oi.setUnitPrice(med.getPrice());
            items.add(oi);
            total = total.add(med.getPrice().multiply(BigDecimal.valueOf(it.quantity())));
        }

        itemRepository.saveAll(items);
        order.setItems(items);
        order.setTotal(total);
        order = orderRepository.save(order);

        return OrderMapper.toDto(order);
    }

    @Transactional
    public void delete(Long id) {
        var o = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderRepository.delete(o);
    }
}
