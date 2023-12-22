package com.gobank.AccountService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Account extends  BaseEntity{
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="accound_NUmber")
    @Id
    private Long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_Address")
    private String branchAddress;
}
