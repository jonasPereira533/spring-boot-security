package br.com.jonas.Spring_boot_essentials.controller;

import br.com.jonas.Spring_boot_essentials.dto.LoginRequestDto;
import br.com.jonas.Spring_boot_essentials.dto.RegisterRequestDto;
import br.com.jonas.Spring_boot_essentials.dto.TokenResponseDto;
import br.com.jonas.Spring_boot_essentials.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public void register (@RequestBody @Valid RegisterRequestDto registerRequestDto) throws Exception {
        authenticationService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto register (@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception {
       return authenticationService.login(loginRequestDto);

    }
}
