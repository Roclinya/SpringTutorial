package com.tutorial.SpringTutorial.InvoiceCompare.service;

import com.tutorial.SpringTutorial.InvoiceCompare.Repository.InvoiceAPIRepository;
import com.tutorial.SpringTutorial.InvoiceCompare.Repository.InvoiceDBRepository;
import com.tutorial.SpringTutorial.InvoiceCompare.vo.InvoiceAPI;
import com.tutorial.SpringTutorial.InvoiceCompare.vo.InvoiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    InvoiceAPIRepository invoiceAPIRepository;

    @Autowired
    InvoiceDBRepository invoiceDBRepository;

    public void compareInvoice() {

        
        //預設從InvoiceAPI,InvoiceDB抓下來的資料已經是同一個vendor的資料做比對
        String vendor = "ASML";
        // 從ERP抓下來的發票資料
        List<InvoiceAPI> invoiceAPIList = invoiceAPIRepository.findAll();// fetchInvoicesFromApi By Vendor
        // 從國稅局網站抓下來的發票資料
        List<InvoiceDB> invoiceDBList = invoiceDBRepository.findAll(); // filterInvoices By Vendor

        // 將ERP發票資料轉換為Map，以便快速查找 (key: invoiceNo, value: InvoiceAPI)
        Map<String, InvoiceAPI> apiInvoiceMap = invoiceAPIList.stream().collect(Collectors.toMap(
                InvoiceAPI::getInvoiceNo,
                invoice -> invoice,
                (existing, replacement) -> existing // 若有重複發票號，保留第一筆
        ));

        // 儲存未匹配的國稅局發票
        List<InvoiceDB> unmatchedInvoices = new ArrayList<>();

        // 比對國稅局的每筆發票
        for (InvoiceDB invoiceDB : invoiceDBList) {
            String invoiceNo = invoiceDB.getInvoiceNo();

            InvoiceAPI matchedApiInvoice = apiInvoiceMap.get(invoiceNo);
            if (matchedApiInvoice == null) {
                // 無對應發票，加入未匹配清單
                unmatchedInvoices.add(invoiceDB);
            } else {
                // 檢查sellerTaxId和amount是否一致
                if (!matchedApiInvoice.getSellerTaxId().equals(invoiceDB.getSellerTaxId()) || matchedApiInvoice.getAmount() != invoiceDB.getAmount()) {
                    // 欄位不一致，視為未匹配對應的發票
                    unmatchedInvoices.add(invoiceDB);
                }

            }
        }

        // 如果有未匹配的發票，發送郵件通知
        if (!unmatchedInvoices.isEmpty()) {
            sendEmailNotification(vendor, unmatchedInvoices);
        }


    }

    private void sendEmailNotification(String vendor, List<InvoiceDB> unmatchedInvoices) {
        // sendEmail(to: "recipient@example.com", subject: "未匹配發票通知", content: emailContent.toString());
        System.out.println("發送郵件給 " + vendor + ":\n" + "recipient@example.com");
        List<String> emailList = new ArrayList<>();
        unmatchedInvoices.forEach(e -> {
            emailList.add(e.getInvoiceNo());
        });
        System.out.println("InvoiceNoList" + ":\n" + emailList);
    }

}
