package com.agent.agent.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    protected Integer id;
    private String title;
    private String firstName;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private String idCard;
    private Boolean validity_of_IDCard ;
    private String phoneNumber;
    private String role;
    private String address;
    private String city;
    private String zipCode;
    private String country;

}
