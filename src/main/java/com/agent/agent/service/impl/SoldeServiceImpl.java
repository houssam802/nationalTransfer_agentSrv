package com.agent.agent.service.impl;

import com.agent.agent.exception.AgentException;
import com.agent.agent.modele.Solde;
import com.agent.agent.repository.SoldeRepository;
import com.agent.agent.service.SoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SoldeServiceImpl implements SoldeService {
    @Autowired
    private SoldeRepository soldeRepository;

    @Override
    public Solde setSolde_transferByCash(Integer id,float amount) {
        Solde solde = soldeRepository.getById(id);
        Double current_amount = solde.getTotal_amount();
        if((solde.getUpdatedAt().getDayOfMonth() - LocalDateTime.now().getDayOfMonth()) == 0){
            if(current_amount <= amount) throw new AgentException("Insufficient balance !");
            else{
                solde.setTotal_amount(current_amount-amount);
            }
        }else{
            solde.setUpdatedAt(LocalDateTime.now());
            solde.setTotal_amount(100000.00-amount);
        }
        return solde;
    }

    @Override
    public Solde setExtortSolde_transferByCash(Integer id, float amount) {
        Solde solde = soldeRepository.getById(id);
        Double current_amount = solde.getTotal_amount();
        solde.setTotal_amount(current_amount+amount);
        return solde;
    }

}
