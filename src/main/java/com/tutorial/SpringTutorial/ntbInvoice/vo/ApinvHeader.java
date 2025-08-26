package com.tutorial.SpringTutorial.ntbInvoice.vo;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "apinv_header")
@IdClass(HeaderContentId.class)
public class ApinvHeader {

    @Id
    @Column(name = "invoice_number", length = 10, nullable = false)
    private String invoiceNumber;

    @Id
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "buyer_note", length = 255)
    private String buyerNote;

    @Column(name = "format_code", length = 2)
    private String formatCode;

    @Column(name = "invoice_status", length = 50)
    private String invoiceStatus;

    @Column(name = "buyer_tax_id", length = 8)
    private String buyerTaxId;

    @Column(name = "buyer_name", length = 255)
    private String buyerName;

    @Column(name = "seller_tax_id", length = 8)
    private String sellerTaxId;

    @Column(name = "seller_name", length = 255)
    private String sellerName;

    @Column(name = "datetime")
    private LocalDate datetime;

    @Column(name = "total_sales_amount", precision = 15, scale = 2)
    private BigDecimal totalSalesAmount;

    @Column(name = "taxable_sales_amount", precision = 15, scale = 2)
    private BigDecimal taxableSalesAmount;

    @Column(name = "zero_tax_sales_amount", precision = 15, scale = 2)
    private BigDecimal zeroTaxSalesAmount;

    @Column(name = "exempt_sales_amount", precision = 15, scale = 2)
    private BigDecimal exemptSalesAmount;

    @Column(name = "business_tax", precision = 15, scale = 2)
    private BigDecimal businessTax;

    @Column(name = "total_amount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "tax_type", length = 50)
    private String taxType;

    @Column(name = "exchange_rate", precision = 10, scale = 4)
    private BigDecimal exchangeRate;

    @Column(name = "carrier_type_code", length = 50)
    private String carrierTypeCode;

    @Column(name = "carrier_number1", length = 50)
    private String carrierNumber1;

    @Column(name = "carrier_number2", length = 50)
    private String carrierNumber2;

    @Column(name = "total_remark", columnDefinition = "TEXT")
    private String totalRemark;

    @Column(name = "confirmation_time")
    private LocalDateTime confirmationTime;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @Column(name = "mig_message_type", length = 10)
    private String migMessageType;

    @Column(name = "sender_tax_id", length = 8)
    private String senderTaxId;

    @Column(name = "sender_name", length = 255)
    private String senderName;
}