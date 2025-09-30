package ufpb.dsc.gestao_de_remedios.Core.Security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ufpb.dsc.gestao_de_remedios.User.Models.User;
import ufpb.dsc.gestao_de_remedios.User.Repositories.UserRepository;

@Configuration
public class Bootstrap {

    private final PasswordEncoder encoder;

    private static final String ADMIN_EMAIL = "admin@remedios.com";
    private static final String ADMIN_PASSWORD = "admin123";

    public Bootstrap(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner initUsers(UserRepository repo) {
        return args -> {
            if (repo.findByEmail(ADMIN_EMAIL).isEmpty()) {
                var u = new User();
                u.setName("Administrador");
                u.setEmail(ADMIN_EMAIL);
                u.setPassword(encoder.encode(ADMIN_PASSWORD));
                u.setRole("ADMIN");
                repo.save(u);
                System.out.println("Usuário ADMIN criado: " + ADMIN_EMAIL);
            }
        };
    }
}
