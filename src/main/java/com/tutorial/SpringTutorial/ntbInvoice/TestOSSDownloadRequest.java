package com.tutorial.SpringTutorial.ntbInvoice;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TestOSSDownloadRequest {

    private List<HeaderContent> header;
    private List<LineContent> lines;

    /**
     * ObjectMapper將InputStream轉Customized DTO
     * @param is
     * @return
     * @throws IOException
     */
    public static TestOSSDownloadRequest fromInputStream(InputStream is) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(is, TestOSSDownloadRequest.class);
    }
}
