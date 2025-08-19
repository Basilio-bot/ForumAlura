package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.TokenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, TokenService tokenService,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public ResponseEntity<TokenDTO> registrar(@RequestBody Usuario usuario) {
        // Criptografar a senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Salvar usuário
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Gerar token automaticamente após registro
        String token = tokenService.gerarToken(usuarioSalvo);

        // 🔥 RETORNAR ResponseEntity COM Content-Type explícito
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TokenDTO(token, "Bearer"));
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha())
        );
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenService.gerarToken(usuario);

        //  RETORNAR ResponseEntity COM Content-Type explícito
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TokenDTO(token, "Bearer"));
    }
}