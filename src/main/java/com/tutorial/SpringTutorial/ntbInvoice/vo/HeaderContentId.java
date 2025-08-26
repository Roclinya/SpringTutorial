package com.tutorial.SpringTutorial.ntbInvoice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class HeaderContentId implements Serializable {

    private String invoiceNumber;
    private LocalDateTime invoiceDate;
}