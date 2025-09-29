package ufpb.dsc.gestao_de_remedios.Order.Models;

import jakarta.persistence.*;
import ufpb.dsc.gestao_de_remedios.Customer.Models.Customer;
import ufpb.dsc.gestao_de_remedios.User.Models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ufpb.dsc.gestao_de_remedios.Customer.Models.Customer getCustomer() { return customer; }
    public void setCustomer(ufpb.dsc.gestao_de_remedios.Customer.Models.Customer customer) { this.customer = customer; }
    public ufpb.dsc.gestao_de_remedios.User.Models.User getUser() { return user; }
    public void setUser(ufpb.dsc.gestao_de_remedios.User.Models.User user) { this.user = user; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
