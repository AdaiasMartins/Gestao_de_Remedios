package ufpb.dsc.gestao_de_remedios.Customer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.dsc.gestao_de_remedios.Customer.Models.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByDocument(String document);
    boolean existsByDocument(String document);
}
