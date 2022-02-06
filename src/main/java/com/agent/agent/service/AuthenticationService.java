package com.agent.agent.service;

import com.agent.agent.DTO.AgentDTO;
import com.agent.agent.DTO.AuthenticationDTO;
import com.agent.agent.DTO.AuthenticationTokenDTO;
import com.agent.agent.modele.Agent;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    AuthenticationTokenDTO authenticate(AuthenticationDTO authenticationDto);
}
