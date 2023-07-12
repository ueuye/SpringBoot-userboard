package com.goodee.mvcBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MvcBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcBoardApplication.class, args);
	}

}
