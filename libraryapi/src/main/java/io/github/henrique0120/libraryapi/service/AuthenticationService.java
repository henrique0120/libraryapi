package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.config.TokenProvider;
import io.github.henrique0120.libraryapi.controller.dto.LoginRequestDTO;
import io.github.henrique0120.libraryapi.controller.dto.TokenResponseDTO;
import io.github.henrique0120.libraryapi.controller.dto.RegisterRequestDTO;
import io.github.henrique0120.libraryapi.enums.RoleType;
import io.github.henrique0120.libraryapi.model.Roles;
import io.github.henrique0120.libraryapi.model.Usuario;
import io.github.henrique0120.libraryapi.repository.RolesRepository;
import io.github.henrique0120.libraryapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private long expirationTime;

    public void criarUsuario(RegisterRequestDTO dto) throws BadRequestException{
        Optional<Usuario> var =  usuarioRepository.findByEmail(dto.getEmail());

        if (var.isPresent()){
            throw new BadRequestException("Já existe um usuario cadastrado com esse e-mail.");
        }

        Roles role = rolesRepository.findByNome(RoleType.ROLE_OPERADOR.name())
                .orElseGet(() -> rolesRepository.save(Roles.builder()
                        .nome(RoleType.ROLE_OPERADOR.name())
                        .build()));


        usuarioRepository.save(Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .roles(Set.of(role))
                .build());
    }

        public TokenResponseDTO login(LoginRequestDTO dto) throws Exception{
            try{
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
                String token = tokenProvider.gerarToken(authentication);

                return new TokenResponseDTO(token, expirationTime);
            }
            catch (BadCredentialsException e){
                throw new BadRequestException("Credenciais inválidas");
            }catch (Exception e){
                throw new Exception("Erro interno inesperado: " + e.getMessage());
            }
        }
}
