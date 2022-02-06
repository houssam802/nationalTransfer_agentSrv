package com.agent.agent.modele;

import com.agent.agent.modele.listeners.AgentListener;
import  lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@EntityListeners({AgentListener.class})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_email" , columnNames = {"email"}),
        @UniqueConstraint(name = "unique_idCard" , columnNames = {"idCard"}),
        @UniqueConstraint(name = "unique_phoneNumber" , columnNames = {"phoneNumber"})
})
@Builder
public class Agent implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id_agent;

    @CreationTimestamp
    @Column(updatable = false)
    protected Instant createdAt;

    @NotNull
    private String title;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(nullable = false)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String birthday;

    @Column(nullable = false)
    private String idCard;

    @NotNull
    private Boolean validity_of_IDCard ;

    @Column(nullable = false)
    private String phoneNumber;


    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String zipCode;

    @NotNull
    private String country;


    @OneToMany(targetEntity = Solde.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Agent" , referencedColumnName = "id_agent")
    private List<Solde> solde;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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



