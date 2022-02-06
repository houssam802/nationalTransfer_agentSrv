package com.agent.agent.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthenticationTokenDTO {
    @NotBlank
    private String token;

    @NotBlank
    private Integer id;
}
