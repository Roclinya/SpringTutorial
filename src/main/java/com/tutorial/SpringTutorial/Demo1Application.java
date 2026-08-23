package com.tutorial.SpringTutorial;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEncryptableProperties
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT10M")
//@ComponentScan(basePackages = "org.springframework.ai.chat")
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
