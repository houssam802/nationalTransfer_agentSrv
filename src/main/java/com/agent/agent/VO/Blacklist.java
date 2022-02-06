package com.agent.agent.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blacklist {

    protected Integer id;
    protected Instant createdAt;
    private Integer idClient;
    private String motif;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Boolean archived;
}
