package com.agent.agent.modele;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Solde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @NotNull
    private LocalDateTime updatedAt = LocalDateTime .now();

    @Column
    private Double total_amount;
}
