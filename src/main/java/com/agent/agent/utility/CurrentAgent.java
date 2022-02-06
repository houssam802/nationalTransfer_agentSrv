package com.agent.agent.utility;

import com.agent.agent.modele.Agent;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentAgent {

    public static Agent get() {
        Agent agent = null;

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof Agent) {
            agent = (Agent) principal;
        }

        return agent;

    }

}
