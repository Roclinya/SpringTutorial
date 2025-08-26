package com.tutorial.SpringTutorial.jxls;

import com.tutorial.SpringTutorial.util.report.ExcelBuilder;
import org.jxls.formula.FastFormulaProcessor;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JxlsService {

    @Autowired
    private ObjectFactory<ExcelBuilder> factory;
    public ResponseEntity<byte[]> executeJxls() throws Exception {

        ExcelBuilder excelBuilder = factory.getObject();
        byte[] cReportContents =  excelBuilder.build();
//        byte[] cReportContents = createContents();

        //本機測試時可以寫入檔案
        java.nio.file.Files.write(java.nio.file.Paths.get("test.xlsx"), cReportContents);
        //下載檔案
        return downloadReport("output_report", "xlsx", cReportContents);
    }

    private ResponseEntity<byte[]> downloadReport(String filename, String fileExtension, byte[] content) throws Exception {
        if (content == null) {
//            throw new CommonException("NO_FILE error");
        }

        Path pathWithFilename = Paths.get("/", filename + "." + fileExtension);
        String contentType = getContentType(pathWithFilename);
        System.out.println("contentType: "+contentType);

        String contentDisposition = getContentDisposition(filename);
        return ResponseEntity.ok().contentLength(content.length).header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION).body(content);
    }

    private String getContentType(Path fullFilePath) throws Exception {
        try {
            return Files.probeContentType(fullFilePath);
        } catch (Exception e) {
//            this.logger.error(StackTraceUtil.logStackTrace(e));
//            throw new CommonException("execution error");
            throw new Exception();
        }
    }

    private String getContentDisposition(String filename) {
        String rtn = "attachment; ";
//        rtn += "filename=\"output_report.xlsx\"; ";
        rtn += "filename=\"" + filename + "\"; ";
        return rtn;
    }


}
