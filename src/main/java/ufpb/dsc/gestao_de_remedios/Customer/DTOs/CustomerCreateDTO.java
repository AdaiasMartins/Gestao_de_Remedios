package ufpb.dsc.gestao_de_remedios.Customer.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CustomerCreateDTO(
        @NotBlank String name,
        @NotBlank String document,
        String phone
) {}
