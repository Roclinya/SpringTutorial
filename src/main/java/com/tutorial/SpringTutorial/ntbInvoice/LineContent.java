package com.tutorial.SpringTutorial.ntbInvoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LineContent {


    @JsonProperty("發票號碼")
    private String invoiceNumber;

    @JsonProperty("發票日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String invoiceDate;

    @JsonProperty("序號")
    private String sequenceNumber;

    @JsonProperty("品名")
    private String itemName;

    @JsonProperty("數量")
    private String quantity;

    @JsonProperty("單位")
    private String unit;

    @JsonProperty("單價")
    private String unitPrice;

    @JsonProperty("金額")
    private String amount;

    @JsonProperty("單一欄位備註")
    private String singleFieldRemark;

    @JsonProperty("相關號碼")
    private String relatedNumber;
}
