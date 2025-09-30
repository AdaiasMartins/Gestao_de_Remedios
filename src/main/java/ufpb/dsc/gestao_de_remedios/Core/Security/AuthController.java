package ufpb.dsc.gestao_de_remedios.Core.Security;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufpb.dsc.gestao_de_remedios.User.Repositories.UserRepository;


import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository userRepo, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public record LoginRequest(@NotBlank String email, @NotBlank String password) {}
    public record ChangePasswordRequest(@NotBlank String oldPassword, @NotBlank String newPassword) {}

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> login(@RequestBody LoginRequest body){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password()));
        var token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());
        return Map.of("token", token);
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {
        var u = userRepo.findByEmail(auth.getName()).orElseThrow();
        return Map.of("id", u.getId(), "name", u.getName(), "email", u.getEmail(), "role", u.getRole());
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Authentication auth, @RequestBody ChangePasswordRequest body) {
        var u = userRepo.findByEmail(auth.getName()).orElseThrow();
        if (!encoder.matches(body.oldPassword(), u.getPassword())) throw new RuntimeException("Invalid password");
        u.setPassword(encoder.encode(body.newPassword()));
        userRepo.save(u);
    }
}
