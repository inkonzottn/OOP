package com.example.oopnp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Pattern(
            regexp = "^[a-zA-Z0-9._]+(@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})?$",
            message = "Email format is invalid"
    )
    @Column(unique = true, nullable = false)
    private String email; // логін

    @NotBlank(message = "First name can`t be empty")
    @Size(min = 2, max = 50, message = "First name must contain 2-50 symbols")
    @Pattern(regexp = "^[A-Za-zА-Яа-яІіЇїЄєҐґ'\\s-]+$", message = "First name can contain only letters")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name can`t be empty")
    @Size(min = 2, max = 50, message = "Last name must contain 2-50 symbols")
    @Pattern(regexp = "^[A-Za-zА-Яа-яІіЇїЄєҐґ'\\s-]+$", message = "Last name can contain only letters")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Password required")
    @Size(min = 8, message = "Password can`t have less than 8 symbols")
    @Column(nullable = false)
    private String password;

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() { return enabled; }
}