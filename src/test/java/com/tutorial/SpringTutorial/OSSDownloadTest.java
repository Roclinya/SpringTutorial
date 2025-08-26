package com.tutorial.SpringTutorial;


import com.tutorial.SpringTutorial.ntbInvoice.TestOSSDownloadRequest;
import com.tutorial.SpringTutorial.ntbInvoice.service.InvoiceDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OSSDownloadTest {

	@Autowired
	private InvoiceDataService invoiceDataService;

	@Test
	public void  OSSWithService() throws Exception {
		String objectKey = "CRAWLER/NtbInvoiceJob/16312227/2025-06-03Test.json";

		File tempFile = new File("/Users/u-hanlin/Downloads/2025-06-03Test.json");
		TestOSSDownloadRequest requestOss = new TestOSSDownloadRequest();

		try (FileInputStream fis = new FileInputStream(tempFile);
		){
//			TestOSSDownloadRequest request = requestOss.fromInputStream(fis);
//			System.out.println("Header Count: " + request.getHeader().size());
//			System.out.println("Lines Count: " + request.getLines().size());

		TestOSSDownloadRequest returnData = invoiceDataService.processAndSaveInvoiceData(fis);
		System.out.println("Finished with : "+ returnData);
	}
}
}
