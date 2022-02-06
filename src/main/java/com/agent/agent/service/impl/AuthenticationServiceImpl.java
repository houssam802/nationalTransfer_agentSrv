package com.agent.agent.service.impl;

import com.agent.agent.DTO.AgentDTO;
import com.agent.agent.DTO.AuthenticationDTO;
import com.agent.agent.DTO.AuthenticationTokenDTO;
import com.agent.agent.controller.converter.AgentConverter;
import com.agent.agent.exception.AgentException;
import com.agent.agent.exception.InvalidFieldException;
import com.agent.agent.modele.Agent;
import com.agent.agent.service.AuthenticationService;
import com.agent.agent.utility.CurrentAgent;
import com.agent.agent.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtUtility jwtUtil;

    private final AgentConverter agentConverter;


    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtUtility jwtUtil, AgentConverter agentConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.agentConverter = agentConverter;
    }

    @Override
    public AuthenticationTokenDTO authenticate(AuthenticationDTO authenticationDto) {
        Authentication authResult;

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationDto.getEmail(),
                    authenticationDto.getPassword()
            );

            authResult = authenticationManager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new InvalidFieldException();
        }

        final Agent agent = (Agent) authResult.getPrincipal();

        String token = jwtUtil.generateToken(agent);

        return AuthenticationTokenDTO.builder()
                .token(token)
                .id(agent.getId_agent())
                .build();
    }

}
