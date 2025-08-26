package com.tutorial.SpringTutorial.InvoiceCompare.controller;

import com.tutorial.SpringTutorial.InvoiceCompare.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/Invoice")
@RestController
public class InvoceController {

    @Autowired
    InvoiceService invoiceService;


    @PostMapping("/compareInvoice")
    public void compareInvoice(){
        invoiceService.compareInvoice();
    }
}
