package com.agent.agent.repository;

import com.agent.agent.modele.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Integer> {

    Optional<Agent> findFirstByEmail(String username);
}
