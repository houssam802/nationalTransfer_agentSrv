package com.agent.agent.controller;


import com.agent.agent.VO.Blacklist;
import com.agent.agent.VO.Client;
import com.agent.agent.service.impl.AgentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_agent")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class ClientCtrl {
    @Autowired
    private AgentServiceImpl agentService;

    @PostMapping("/client")
    public ResponseEntity<?> createCurrentUser(@RequestBody Client client) {
        return agentService.createClient(client);
    }

    @GetMapping("/client/account/{id}")
    public ResponseEntity<?> VerifyAccount(@PathVariable("id") Integer id,
                                                 @RequestParam("amount") double amount) {
        return agentService.VerifyAccount(id,amount);
    }

    @GetMapping("/client/cin/{cin}")
    public ResponseEntity<?> getCurrentUserByCin(@PathVariable("cin") String cin) {
        return agentService.getClientByCin(cin);
    }

    @GetMapping("/client/ph/{phoneNumber}")
    public ResponseEntity<?> getClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return agentService.getClientByPhoneNumber(phoneNumber);
    }

    @GetMapping("/client/account/get/{account}")
    public ResponseEntity<?> getClientByAccountNumber(@PathVariable("account") String account) {
        return agentService.getClientByAccountNumber(account);
    }


    @GetMapping("/client/recipients/{id}")
    public ResponseEntity<?> getClientRecipients(@PathVariable("id") Integer id) {
        return agentService.getClientRecipients(id);
    }

    @PostMapping("/blacklist/client")
    public ResponseEntity<?> checkClientINBlacklist(@RequestBody Blacklist client){
        return agentService.checkClientINBlacklist(client);
    }

}
