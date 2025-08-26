package com.tutorial.SpringTutorial.ntbInvoice.vo;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "apinv_line")
public class ApinvLine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_number", length = 10, nullable = false)
    private String invoiceNumber;

    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "sequence_number", length = 10)
    private String sequenceNumber;

    @Column(name = "item_name", length = 255)
    private String itemName;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "unit_price", precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "single_field_remark", length = 255)
    private String singleFieldRemark;

    @Column(name = "related_number", length = 50)
    private String relatedNumber;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "invoice_number", referencedColumnName = "invoice_number", insertable = false, updatable = false),
            @JoinColumn(name = "invoice_date", referencedColumnName = "invoice_date", insertable = false, updatable = false)
    })
    private ApinvHeader header;
}