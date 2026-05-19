package br.com.jonas.Spring_boot_essentials.service;

import br.com.jonas.Spring_boot_essentials.config.TokenProvider;
import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.RolesEntity;
import br.com.jonas.Spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.IRolesReposotiry;
import br.com.jonas.Spring_boot_essentials.dto.LoginRequestDto;
import br.com.jonas.Spring_boot_essentials.dto.RegisterRequestDto;
import br.com.jonas.Spring_boot_essentials.dto.TokenResponseDto;
import br.com.jonas.Spring_boot_essentials.enums.RoleTypeEnum;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final IAlunosRepository alunosRepository;
    private final IRolesReposotiry rolesReposotiry;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider  tokenProvider;
    @Value("${JWT_EXPIRATION:900000}")
    private long expirationTime;



    public void register(RegisterRequestDto dto) throws BadRequestException {
        AlunosAntity aluno = alunosRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (aluno != null){
            throw new BadRequestException("aluno já cadastrado");
        }

        RolesEntity role = rolesReposotiry.findByNome(RoleTypeEnum.ROLE_ALUNO.name())
                .orElseGet(() -> rolesReposotiry.save(RolesEntity.builder()
                        .nome(RoleTypeEnum.ROLE_ALUNO.name())
                        .build()));

        alunosRepository.save(AlunosAntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build());
    }

    public TokenResponseDto  login(LoginRequestDto dto) throws BadRequestException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
            String token = tokenProvider.gerarToken(authentication);

            return new TokenResponseDto(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Email ou senha invalidas");
        }
         catch (Exception e) {
            throw e;
         }
    }
}
