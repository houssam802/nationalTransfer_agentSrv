package com.agent.agent.service;

import com.agent.agent.VO.Blacklist;
import com.agent.agent.VO.Multitransfer;
import com.agent.agent.VO.Transfer;
import com.agent.agent.VO.Client;
import com.agent.agent.modele.Agent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AgentService extends UserDetailsService {
    Agent createAgent(Agent agent);

    Agent getAgentbyId(Integer id);

    ResponseEntity<?> createMultitransferAgent_byCash(Multitransfer multitransfer);

    ResponseEntity<?> getTransferByReference(String reference);

    void setSolde_transferByCash(Agent agent, float amount);

    void setExtortSolde_transferByCash(Agent agent, float amount);

    ResponseEntity<?> getTransferByAgent(Integer id,Integer page);

    ResponseEntity<?> createMultitransferAgent(Multitransfer multitransfer);

    ResponseEntity<?> serveTransferByReference(String reference);

    ResponseEntity<?> serveWalletTransferByReference(String reference);

    ResponseEntity<?> getWalletTransferByReference(String reference);

    ResponseEntity<?> extortTransferByReference(String reference, String motif);

    ResponseEntity<?> createClient(Client user);

    ResponseEntity<?> VerifyAccount(Integer id_client,double amount);

    ResponseEntity<?> checkRecipientInfosByReference(String reference, Transfer transfer);

    ResponseEntity<?> checkTransfer_code_pin(String reference, String pin_code);

    ResponseEntity<?> getClientByCin(String cin);

    ResponseEntity<?> getClientByPhoneNumber(String phoneNumber);

    ResponseEntity<?> getClientRecipients(Integer id);

    ResponseEntity<?> countTransferById(Integer id);

    ResponseEntity<?> sendOtpToClient(String otp, String phone_number);

    ResponseEntity<?> checkClientINBlacklist(Blacklist client);

    ResponseEntity<?> getTransferByAgentAll(Integer id);

    ResponseEntity<?> getClientByAccountNumber(String account);
}
