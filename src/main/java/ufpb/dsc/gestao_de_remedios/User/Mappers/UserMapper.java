package ufpb.dsc.gestao_de_remedios.User.Mappers;

import ufpb.dsc.gestao_de_remedios.User.DTOs.*;
import ufpb.dsc.gestao_de_remedios.User.Models.User;

public class UserMapper {

    public static User toEntity(UserCreateDTO dto) {
        var u = new User();
        u.setName(dto.name());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setRole(dto.role());
        return u;
    }

    public static void updateEntity(User u, UserUpdateDTO dto) {
        u.setName(dto.name());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setRole(dto.role());
    }

    public static UserResponseDTO toDto(User u) {
        return new UserResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getRole());
    }
}
