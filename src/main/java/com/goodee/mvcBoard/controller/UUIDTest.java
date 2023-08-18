package com.goodee.mvcBoard.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UUIDTest {
	@GetMapping("UUIDTest")
	public String uuidTest() {
		UUID uuid = UUID.randomUUID();
		log.debug("\u001B[45m" + uuid.toString().replace("-", "") + "\u001B[0m");
		return "";
	}
}
