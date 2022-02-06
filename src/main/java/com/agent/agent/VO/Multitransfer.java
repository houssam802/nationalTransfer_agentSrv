package com.agent.agent.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multitransfer {

    private LocalDateTime created_at = LocalDateTime .now();
    private LocalDateTime  ended_at = created_at.plusDays(10);
    private Integer id_client=0;
    private String sender_fname;
    private String sender_lname;
    private String sender_phnumber;
    private Float total_amount;
    private Float total_expense_amount;
    private int expense_id;
    private boolean transfer_by_cash;
    private boolean notify_transfer;
    private int client_id;
    private boolean request_by_prospect_client;
    private int sended_by_agent;
    private String motif;
    private List<Transfer> transfers;

    private int id_multitransfer;

}
