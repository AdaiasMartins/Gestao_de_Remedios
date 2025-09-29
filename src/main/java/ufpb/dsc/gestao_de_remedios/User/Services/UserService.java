package ufpb.dsc.gestao_de_remedios.User.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufpb.dsc.gestao_de_remedios.User.DTOs.*;
import ufpb.dsc.gestao_de_remedios.User.Mappers.UserMapper;
import ufpb.dsc.gestao_de_remedios.User.Models.User;
import ufpb.dsc.gestao_de_remedios.User.Repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repo) {
        this.repository = repo;
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        User saved = repository.save(UserMapper.toEntity(dto));
        return UserMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> list() {
        return repository.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO get(Long id) {
        User u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserMapper.toDto(u);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        User u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserMapper.updateEntity(u, dto);
        User updated = repository.save(u);
        return UserMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("User not found");
        repository.deleteById(id);
    }
}
