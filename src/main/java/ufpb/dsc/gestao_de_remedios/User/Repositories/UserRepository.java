package ufpb.dsc.gestao_de_remedios.User.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufpb.dsc.gestao_de_remedios.User.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
