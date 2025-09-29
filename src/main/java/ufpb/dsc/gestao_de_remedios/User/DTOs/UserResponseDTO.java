package ufpb.dsc.gestao_de_remedios.User.DTOs;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String role
) {}
