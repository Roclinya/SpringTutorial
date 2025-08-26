package com.tutorial.SpringTutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sl4jexecute {
	
	private static Logger logger = LoggerFactory.getLogger(Sl4jexecute.class);
	@RequestMapping(value = "/sl4j")
	public static void main(String[] args) {
		
		//日誌管理輸出層級
		logger.trace("Trace message");
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
	}

}
