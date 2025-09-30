package ufpb.dsc.gestao_de_remedios.User.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufpb.dsc.gestao_de_remedios.User.DTOs.*;
import ufpb.dsc.gestao_de_remedios.User.Mappers.UserMapper;
import ufpb.dsc.gestao_de_remedios.User.Models.User;
import ufpb.dsc.gestao_de_remedios.User.Repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repository = repo;
        this.encoder = encoder;
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        var u = UserMapper.toEntity(dto);
        u.setPassword(encoder.encode(u.getPassword()));
        return UserMapper.toDto(repository.save(u));
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
        var u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserMapper.updateEntity(u, dto);
        u.setPassword(encoder.encode(u.getPassword()));
        return UserMapper.toDto(repository.save(u));
    }
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("User not found");
        repository.deleteById(id);
    }
}
