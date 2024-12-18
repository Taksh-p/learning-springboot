package com.example.SpringSecurity.SpringSecurityApplication.entities;

import com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Role;
import com.example.SpringSecurity.SpringSecurityApplication.utills.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> authority = new HashSet<>();

        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(role);
                    authority.addAll(permissions);
                }
        );
        return authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
