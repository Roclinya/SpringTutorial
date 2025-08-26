package com.tutorial.SpringTutorial.InvoiceCompare.vo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "invoiceDB")
public class InvoiceDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String vendorId;
    private String invoiceNo;
    private String sellerTaxId;
    private double amount;
    private LocalDateTime createdAt;

}
