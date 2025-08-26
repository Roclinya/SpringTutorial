package com.tutorial.SpringTutorial.ntbInvoice.service;


import com.tutorial.SpringTutorial.ntbInvoice.HeaderContent;
import com.tutorial.SpringTutorial.ntbInvoice.LineContent;
import com.tutorial.SpringTutorial.ntbInvoice.TestOSSDownloadRequest;
import com.tutorial.SpringTutorial.ntbInvoice.repo.ApinvHeaderRepository;
import com.tutorial.SpringTutorial.ntbInvoice.repo.ApinvLineRepository;
import com.tutorial.SpringTutorial.ntbInvoice.vo.ApinvHeader;
import com.tutorial.SpringTutorial.ntbInvoice.vo.ApinvLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class InvoiceDataService {

    @Autowired
    private ApinvHeaderRepository headerRepository;

    @Autowired
    private ApinvLineRepository lineRepository;

//    @Autowired
//    private OSSUtil ossUtil;

    @Transactional
    public TestOSSDownloadRequest processAndSaveInvoiceData(FileInputStream fis) throws Exception {
        // Download file from OSS
//        InputStream fis = ossUtil.downloadFile(objectKey);
        TestOSSDownloadRequest request = TestOSSDownloadRequest.fromInputStream(fis);
        System.out.println("Header Count: " + request.getHeader().size());
        System.out.println("Lines Count: " + request.getLines().size());

        // Process headers
        for (HeaderContent headerContent : request.getHeader()) {
            ApinvHeader header = mapToApinvHeader(headerContent);
            headerRepository.save(header);

            // Process lines associated with this header
            for (LineContent lineContent : request.getLines()) {
                if (lineContent.getInvoiceNumber().equals(headerContent.getInvoiceNumber()) &&
                        lineContent.getInvoiceDate().equals(headerContent.getInvoiceDate())) {
                    ApinvLine line = mapToApinvLine(lineContent, header);
                    lineRepository.save(line);
                }
            }
        }
        return request;
    }

    private ApinvHeader mapToApinvHeader(HeaderContent headerContent) {
        ApinvHeader header = new ApinvHeader();
        header.setInvoiceNumber(headerContent.getInvoiceNumber());
        header.setInvoiceDate(parseDateTime(headerContent.getInvoiceDate()));
        header.setBuyerNote(headerContent.getBuyerNote());
        header.setFormatCode(headerContent.getFormatCode());
        header.setInvoiceStatus(headerContent.getInvoiceStatus());
        header.setBuyerTaxId(headerContent.getBuyerTaxId());
        header.setBuyerName(headerContent.getBuyerName());
        header.setSellerTaxId(headerContent.getSellerTaxId());
        header.setSellerName(headerContent.getSellerName());
        header.setDatetime(parseDate(headerContent.getDatetime()));
        header.setTotalSalesAmount(parseBigDecimal(headerContent.getTotalSalesAmount()));
        header.setTaxableSalesAmount(parseBigDecimal(headerContent.getTaxableSalesAmount()));
        header.setZeroTaxSalesAmount(parseBigDecimal(headerContent.getZeroTaxSalesAmount()));
        header.setExemptSalesAmount(parseBigDecimal(headerContent.getExemptSalesAmount()));
        header.setBusinessTax(parseBigDecimal(headerContent.getBusinessTax()));
        header.setTotalAmount(parseBigDecimal(headerContent.getTotalAmount()));
        header.setTaxType(headerContent.getTaxType());
        header.setExchangeRate(parseBigDecimal(headerContent.getExchangeRate()));
        header.setCarrierTypeCode(headerContent.getCarrierTypeCode());
        header.setCarrierNumber1(headerContent.getCarrierNumber1());
        header.setCarrierNumber2(headerContent.getCarrierNumber2());
        header.setTotalRemark(headerContent.getTotalRemark());
        header.setConfirmationTime(parseDateTime(headerContent.getConfirmationTime()));
        header.setLastUpdateTime(parseDateTime(headerContent.getLastUpdateTime()));
        header.setMigMessageType(headerContent.getMigMessageType());
        header.setSenderTaxId(headerContent.getSenderTaxId());
        header.setSenderName(headerContent.getSenderName());
        return header;
    }

    private ApinvLine mapToApinvLine(LineContent lineContent, ApinvHeader header) {
        ApinvLine line = new ApinvLine();
        line.setInvoiceNumber(lineContent.getInvoiceNumber());
        line.setInvoiceDate(parseDateTime(lineContent.getInvoiceDate()));
        line.setSequenceNumber(lineContent.getSequenceNumber());
        line.setItemName(lineContent.getItemName());
        line.setQuantity(parseBigDecimal(lineContent.getQuantity()));
        line.setUnit(lineContent.getUnit());
        line.setUnitPrice(parseBigDecimal(lineContent.getUnitPrice()));
        line.setAmount(parseBigDecimal(lineContent.getAmount()));
        line.setSingleFieldRemark(lineContent.getSingleFieldRemark());
        line.setRelatedNumber(lineContent.getRelatedNumber());
        line.setHeader(header);
        return line;
    }

    private LocalDateTime parseDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

//    private Date parseDateTime(String dateTime) {
//        if (dateTime == null || dateTime.isEmpty()) {
//            return null;
//        }
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            return formatter.parse(dateTime);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
