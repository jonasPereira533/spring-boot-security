package br.com.jonas.Spring_boot_essentials.service;

import br.com.jonas.Spring_boot_essentials.database.repository.IAlunosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDatailsmpl implements UserDetailsService {

    private final IAlunosRepository iAlunosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iAlunosRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("esse usuário não foi encontrado" + username));
    }
}
