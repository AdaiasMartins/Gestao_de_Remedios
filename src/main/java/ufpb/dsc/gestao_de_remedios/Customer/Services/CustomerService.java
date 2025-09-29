package ufpb.dsc.gestao_de_remedios.Customer.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufpb.dsc.gestao_de_remedios.Customer.DTOs.*;
import ufpb.dsc.gestao_de_remedios.Customer.Mappers.CustomerMapper;
import ufpb.dsc.gestao_de_remedios.Customer.Models.Customer;
import ufpb.dsc.gestao_de_remedios.Customer.Repositories.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerResponseDTO create(CustomerCreateDTO dto) {
        Customer saved = repository.save(CustomerMapper.toEntity(dto));
        return CustomerMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> list() {
        return repository.findAll().stream().map(CustomerMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO get(Long id) {
        Customer c = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return CustomerMapper.toDto(c);
    }

    @Transactional
    public CustomerResponseDTO update(Long id, CustomerUpdateDTO dto) {
        Customer c = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        CustomerMapper.updateEntity(c, dto);
        Customer updated = repository.save(c);
        return CustomerMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Customer not found");
        repository.deleteById(id);
    }
}
