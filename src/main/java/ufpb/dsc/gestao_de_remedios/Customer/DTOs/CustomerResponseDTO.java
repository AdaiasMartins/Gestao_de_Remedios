package ufpb.dsc.gestao_de_remedios.Customer.DTOs;

public record CustomerResponseDTO(
        Long id,
        String name,
        String document,
        String phone
) {}
