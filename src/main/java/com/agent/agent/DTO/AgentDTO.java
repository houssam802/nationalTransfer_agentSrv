package com.agent.agent.DTO;


import com.agent.agent.modele.Solde;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AgentDTO {
    @NotNull
    private Integer id_agent;

    @NotNull
    private String title;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String idCard;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String country;

    private List<Solde> solde;


}
