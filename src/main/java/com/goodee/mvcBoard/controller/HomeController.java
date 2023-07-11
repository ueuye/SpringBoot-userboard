package com.goodee.mvcBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ... implements Servlet
public class HomeController {
	@GetMapping("/home") // web.xml 의 url 패턴매핑을 대신한다 or 애노테이션 web servlet
	public String home() {
		return "home"; // RequestDispatcher.forward() 대신한다
	}
}
