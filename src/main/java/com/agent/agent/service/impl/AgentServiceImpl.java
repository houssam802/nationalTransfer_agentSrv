package com.agent.agent.service.impl;


import com.agent.agent.VO.Blacklist;
import com.agent.agent.VO.Multitransfer;
import com.agent.agent.VO.Transfer;
import com.agent.agent.VO.Client;
import com.agent.agent.handler.RestTemplateResponseErrorHandler;
import com.agent.agent.modele.Agent;
import com.agent.agent.modele.Solde;
import com.agent.agent.repository.AgentRepository;
import com.agent.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    private final String transfer_rest_url = "http://TRANSFER-SERVICE/api_transfer/";
    //private final String transfer_rest_url = "http://localhost:8082/api_transfer/";
    private final String client_rest_url = "http://localhost:9000/api_client/";
    private final String backoffice_rest_url = "http://localhost:8083/api/backoffice/";

    @Autowired
    private SoldeServiceImpl soldeService;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private RestTemplate restTemplate;


    private Solde initialSolde = new Solde();


    @Override
    public Agent createAgent(Agent agent) {
        initialSolde.setTotal_amount(100000.00);
        List<Solde> soldes = new ArrayList<>();
        soldes.add(initialSolde);
        agent.setSolde(soldes);
        return agentRepository.save(agent);
    }

    @Override
    public Agent getAgentbyId(Integer id) {
        return agentRepository.findById(id).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return agentRepository
                .findFirstByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Username %s not found.", username))
                );
    }

    @Override
    public void setSolde_transferByCash(Agent agent, float amount) {
        Solde solde = soldeService.setSolde_transferByCash(agent.getSolde().get(0).getId(),amount);
        List<Solde> soldes = new ArrayList<>();
        soldes.add(solde);
        agent.setSolde(soldes);
        agentRepository.save(agent);
    }

    @Override
    public void setExtortSolde_transferByCash(Agent agent, float amount) {
        Solde solde = soldeService.setExtortSolde_transferByCash(agent.getSolde().get(0).getId(),amount);
        List<Solde> soldes = new ArrayList<>();
        soldes.add(solde);
        agent.setSolde(soldes);
        agentRepository.save(agent);
    }


    @Override
    public ResponseEntity<?> createMultitransferAgent_byCash(Multitransfer multitransfer) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(multitransfer,headers);
        return restTemplate.postForEntity(transfer_rest_url+"createTransfer/agent/byCash",entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> createMultitransferAgent(Multitransfer multitransfer) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(multitransfer,headers);
        return restTemplate.postForEntity(transfer_rest_url+"createTransfer/agent",entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> serveTransferByReference(String reference) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"UniqueTransfer/serve/"+reference,HttpMethod.PUT,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> serveWalletTransferByReference(String reference) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"UniqueTransfer/serve/"+reference,HttpMethod.PUT,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> checkRecipientInfosByReference(String reference, Transfer transfer) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(transfer,headers);
        return restTemplate.exchange(transfer_rest_url+"UniqueTransfer/serve/"+reference,HttpMethod.POST,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> checkTransfer_code_pin(String reference, String pin_code) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"UniqueTransfer/pin_code/"+reference+"?code_pin="+pin_code, Object.class,1);
    }

    @Override
    public ResponseEntity<?> extortTransferByReference(String reference, String motif) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"UniqueTransfer/extort/"+reference+"?motif="+motif,HttpMethod.PUT,entity,Object.class,1);
    }


    @Override
    public ResponseEntity<?> getTransferByReference(String reference) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"UniqueTransfer/"+reference, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getWalletTransferByReference(String reference) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"UniqueTransfer/wallet/"+reference, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getTransferByAgent(Integer id,Integer page) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"MultiTransfer/agent"+"?idAgent="+id+"&page="+page, Object.class,1);
    }


    @Override
    public ResponseEntity<?> getTransferByAgentAll(Integer id) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"MultiTransfers/all"+"?idAgent="+id, Object.class,1);
    }


    @Override
    public ResponseEntity<?> countTransferById(Integer id) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"MultiTransfer/count/agent"+"?idAgent="+id, Object.class,1);
    }




    @Override
    public ResponseEntity<?> createClient(Client user) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(user,headers);
        return restTemplate.exchange(client_rest_url+"/client",HttpMethod.POST,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> VerifyAccount(Integer id_client, double amount) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/client/"+id_client+"/"+amount, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClientByCin(String cin) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/cin/"+cin, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClientByPhoneNumber(String phoneNumber) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/ph/"+phoneNumber, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClientRecipients(Integer id) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/beneficiaires/agent/"+id, Object.class,1);
    }

    @Override
    public ResponseEntity<?> sendOtpToClient(String otp, String phone_number) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"/MultiTransfer/client/otp?"+"otp="+otp+"&phone_number="+phone_number,HttpMethod.POST,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClientByAccountNumber(String account) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/nc/"+account, Object.class,1);
    }

    @Override
    public ResponseEntity<?> checkClientINBlacklist(Blacklist client) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(client,headers);
        return restTemplate.exchange(backoffice_rest_url+"/blacklist/client",HttpMethod.POST,entity,Object.class,1);
    }



}
