package com.goodee.mvcBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Htmlcontroller {
	@GetMapping("/html/localNameList")
	public String localNameList() {
		return "/html/localNameList";
	}
	
}
