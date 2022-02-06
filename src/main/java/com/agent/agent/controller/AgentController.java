package com.agent.agent.controller;

import com.agent.agent.DTO.AgentDTO;
import com.agent.agent.DTO.AuthenticationDTO;
import com.agent.agent.DTO.AuthenticationTokenDTO;
import com.agent.agent.controller.converter.AgentConverter;
import com.agent.agent.modele.Agent;
import com.agent.agent.service.impl.AgentServiceImpl;
import com.agent.agent.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api_agent")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class AgentController {

    private final AgentConverter agentConverter;

    @Autowired
    private AgentServiceImpl agentService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    public AgentController(AgentConverter agentConverter) {
        this.agentConverter = agentConverter;
    }

    @PostMapping("/createAgent")
    public ResponseEntity<Integer> createAgent(@Valid @RequestBody Agent agent){
        Agent agent_tmp = agentService.createAgent(agent);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(agent_tmp.getId_agent());
    }

    @PostMapping("/login")
    public AuthenticationTokenDTO login(@Valid @RequestBody AuthenticationDTO authenticationDto) {
        return authenticationService.authenticate(authenticationDto);
    }


    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("id") Integer id) {
        Agent agent = agentService.getAgentbyId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(agentConverter.convertToDTO(agent));
    }

}
