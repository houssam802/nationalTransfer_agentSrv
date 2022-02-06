package com.agent.agent.repository;

import com.agent.agent.modele.Solde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldeRepository extends JpaRepository<Solde,Integer> {

    @Query(value = "SELECT * FROM agent WHERE id_agent = ?1",
            nativeQuery = true)
    Solde getByIdAgent(Integer id_agent);
}
