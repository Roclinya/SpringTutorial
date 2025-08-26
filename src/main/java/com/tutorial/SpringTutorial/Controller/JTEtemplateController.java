package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Service.JTEBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@RequestMapping("/api")
@RestController
public class JTEtemplateController {

   @Autowired
    JTEBuilder jteBuilder;

    @GetMapping(value="/generateReport", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> createJTE() throws URISyntaxException {
        try {
            String htmlContent = jteBuilder.build();
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating report: " + e.getMessage());
        }
    }

    @GetMapping(value = "/downloadReport", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> downloadReport() {
        try {
            String htmlContent = jteBuilder.build();

            // 设置下载头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.html");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.TEXT_HTML)
                    .body(htmlContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating report: " + e.getMessage());
        }
    }


    @GetMapping(value = "/downloadReport2", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> downloadReport2() {
        try {
//            String htmlContent = jteBuilder.build();
            readFileBetterPerformance("input.txt");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 向ByteArrayOutputStream中写入数据
            baos.write("Hello, World!".getBytes());
            byte[] outBytes = baos.toByteArray();
            String htmlContent =new String(outBytes, StandardCharsets.UTF_8);

            // 设置下载头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.html");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.TEXT_HTML)
                    .body(htmlContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating report: " + e.getMessage());
        }
    }

    private static void readFileBetterPerformance(String fileName) {

        try (FileInputStream fis = new FileInputStream(new File(fileName))) {

            // remaining bytes that can be read
            System.out.println("Remaining bytes that can be read : " + fis.available());

            // 8k a time
            byte[] bytes = new byte[20];

            // reads 8192 bytes at a time, if end of the file, returns -1
            while (fis.read(bytes) != -1) {

                // convert bytes to string for demo
                System.out.println(new String(bytes, StandardCharsets.UTF_8));

                System.out.println("Remaining bytes that can be read : " + fis.available());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
