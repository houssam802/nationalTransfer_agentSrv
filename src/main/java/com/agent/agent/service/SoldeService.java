package com.agent.agent.service;

import com.agent.agent.modele.Solde;

import java.util.List;

public interface SoldeService {
    Solde setSolde_transferByCash(Integer id,float amount);

    Solde setExtortSolde_transferByCash(Integer id, float amount);
}
