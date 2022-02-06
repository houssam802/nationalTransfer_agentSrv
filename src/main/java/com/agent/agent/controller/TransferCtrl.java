package com.agent.agent.controller;

import com.agent.agent.DTO.AgentDTO;
import com.agent.agent.VO.Multitransfer;
import com.agent.agent.VO.Transfer;
import com.agent.agent.controller.converter.AgentConverter;
import com.agent.agent.modele.Agent;
import com.agent.agent.service.impl.AgentServiceImpl;
import com.agent.agent.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api_agent")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class TransferCtrl {

    @Autowired
    private AgentServiceImpl agentService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/createTransfer/byCash")
    public ResponseEntity<?> createMultitransferAgent_byCash(@Valid @RequestBody Multitransfer multitransfer
                                                ,@RequestHeader("id") Integer id){

        multitransfer.setSended_by_agent(id);
        agentService.setSolde_transferByCash(agentService.getAgentbyId(id),multitransfer.getTotal_amount());
        return agentService.createMultitransferAgent_byCash(multitransfer);
    }

    @PostMapping("/createTransfer")
    public ResponseEntity<?> createMultitransferAgent(@Valid @RequestBody Multitransfer multitransfer
            ,@RequestHeader("id") Integer id){

        multitransfer.setSended_by_agent(id);
        return agentService.createMultitransferAgent(multitransfer);
    }

    @GetMapping("/UniqueTransfer/{reference}")
    public ResponseEntity<?> getTransferByReference(@PathVariable("reference") String reference){
        return (agentService.getTransferByReference(reference));
    }

    @GetMapping("/UniqueTransfer/wallet/{reference}")
    public ResponseEntity<?> getWalletTransferByReference(@PathVariable("reference") String reference){
        return (agentService.getWalletTransferByReference(reference));
    }

    @GetMapping("/Multitransfer")
    public ResponseEntity<?> getTransferById(@RequestHeader("id") Integer id
                            ,@RequestParam("page") Integer page){
        return (agentService.getTransferByAgent(id,page));
    }

    @GetMapping("/Multitransfer/count")
    public ResponseEntity<?> countTransferById(@RequestHeader("id") Integer id){
        return (agentService.countTransferById(id));
    }

    @GetMapping("/Multitransfers/all")
    public ResponseEntity<?> getMultiTransferByidAgentAll(@RequestHeader("id") Integer id){
        return (agentService.getTransferByAgentAll(id));
    }


    @PostMapping("/MultiTransfer/client/otp")
    public ResponseEntity<?> sendOtpToClient(@RequestParam("otp") String otp ,  @RequestParam("phone_number") String phone_number){
        return (agentService.sendOtpToClient(otp,phone_number));
    }

    @PutMapping("/UniqueTransfer/serve/{reference}")
    public ResponseEntity<?> serveTransferByReference(@PathVariable("reference") String reference,
                                                                     @RequestHeader("id") Integer id){
        return (agentService.serveTransferByReference(reference));
    }

    @PutMapping("/UniqueTransfer/wallet/serve/{reference}")
    public ResponseEntity<?> serveWalletTransferByReference(@PathVariable("reference") String reference,
                                                      @RequestHeader("id") Integer id){
        return (agentService.serveWalletTransferByReference(reference));
    }

    @PostMapping("/UniqueTransfer/serve/{reference}")
    public ResponseEntity<?> checkRecipientInfosByReference(@PathVariable("reference") String reference,
                                                      @RequestHeader("id") Integer id,
                                                            @RequestBody Transfer transfer){
        return (agentService.checkRecipientInfosByReference(reference,transfer));
    }

    @PutMapping("/UniqueTransfer/extort/{reference}")
    public ResponseEntity<?> extortTransferByReference(@PathVariable("reference") String reference,
                                                                      @RequestParam("motif") String motif,
                                                                      @RequestHeader("id") Integer id){

        LinkedHashMap multitransfer = (LinkedHashMap) agentService.getTransferByReference(reference).getBody();
        float amount = ((Double) ((LinkedHashMap) ((ArrayList) multitransfer.get("transfers")).get(0)).get("transfer_amount")).floatValue();
        agentService.setExtortSolde_transferByCash(agentService.getAgentbyId(id),amount);
        return (agentService.extortTransferByReference(reference,motif));

    }

    @GetMapping("/UniqueTransfer/pin_code/{reference}")
    public ResponseEntity<?> checkTransfer_code_pin(@PathVariable("reference") String reference,
                                                                   @RequestParam("code_pin") String pin_code){
        return agentService.checkTransfer_code_pin(reference,pin_code);
    }
}
