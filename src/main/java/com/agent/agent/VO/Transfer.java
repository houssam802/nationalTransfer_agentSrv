package com.agent.agent.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private float transfer_amount;
    private String transfer_reference ;
    private int transfer_status;
    private Date received_at;
    private String receiver_fname;
    private String receiver_lname;
    private String receiver_phnumber;
    private String id_multitransfer;
    private String motif="";
    private Integer id_transfer;

}
