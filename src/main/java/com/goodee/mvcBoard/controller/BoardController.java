package com.goodee.mvcBoard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcBoard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							// 형변환을 따로 안해도 된다. null값이 넘어오면 defaultValue값으로 저장
							@RequestParam(value = "currentPage", defaultValue = "1") int currentPage, 
							@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String, Object> resultMap= boardService.getBoardList(currentPage, rowPerPage);
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("localNameList",resultMap.get("localNameList")); // request.setAttribute() 와 비슷한 역할
		model.addAttribute("boardList",resultMap.get("boardList"));
		
		model.addAttribute("lastPage",resultMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		
		return "/board/boardList";
	}
}
