package com.gobank.Service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loan_service")
public class LoanEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator =  "native")
    @GenericGenerator(name = "native",strategy = "native")

    private Long loanId;
    private  String mobileNumber;
    private String loanNumber;
    private  String loanType;
    private  int totalLoan;
    private  int amountPaid;
    private int outstandingAmount;


}
