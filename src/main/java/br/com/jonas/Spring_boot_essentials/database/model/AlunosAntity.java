package br.com.jonas.Spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class AlunosAntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Padrão utiliar o EAGER
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},  fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_fisica_id")
    private AvaliacoesFisicasEntity avaliacaoFisica;


    // Padrão utiliar o LAZY
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<TreinosAntity> treino = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "alunos_role",
        joinColumns = @JoinColumn (name = "aluno_id"),
        inverseJoinColumns = @JoinColumn (name = "role_id"))
    @Builder.Default
    private Set<RolesEntity> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
