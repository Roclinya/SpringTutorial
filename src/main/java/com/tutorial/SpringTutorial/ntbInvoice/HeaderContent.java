package com.tutorial.SpringTutorial.ntbInvoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeaderContent {


    @JsonProperty("發票號碼")
    private String invoiceNumber;

    @JsonProperty("買受人註記")
    private String buyerNote;

    @JsonProperty("格式代號")
    private String formatCode;

    @JsonProperty("發票狀態")
    private String invoiceStatus;

    @JsonProperty("發票日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String invoiceDate;

    @JsonProperty("買方統一編號")
    private String buyerTaxId;

    @JsonProperty("買方名稱")
    private String buyerName;

    @JsonProperty("賣方統一編號")
    private String sellerTaxId;

    @JsonProperty("賣方名稱")
    private String sellerName;

    @JsonProperty("寄送日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String datetime;

    @JsonProperty("銷售額合計")
    private String totalSalesAmount;

    @JsonProperty("應稅銷售額")
    private String taxableSalesAmount;

    @JsonProperty("零稅銷售額")
    private String zeroTaxSalesAmount;

    @JsonProperty("免稅銷售額")
    private String exemptSalesAmount;

    @JsonProperty("營業稅")
    private String businessTax;

    @JsonProperty("總計")
    private String totalAmount;

    @JsonProperty("課稅別")
    private String taxType;

    @JsonProperty("匯率")
    private String exchangeRate;

    @JsonProperty("載具類別編號")
    private String carrierTypeCode;

    @JsonProperty("載具號碼1")
    private String carrierNumber1;

    @JsonProperty("載具號碼2")
    private String carrierNumber2;

    @JsonProperty("總備註")
    private String totalRemark;

    @JsonProperty("開立確認時間")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String confirmationTime;

    @JsonProperty("最後異動時間")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastUpdateTime;

    @JsonProperty("MIG訊息類別")
    private String migMessageType;

    @JsonProperty("傳送方統編")
    private String senderTaxId;

    @JsonProperty("傳送方名稱")
    private String senderName;

}
