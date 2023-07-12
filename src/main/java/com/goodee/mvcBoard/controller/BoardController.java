package com.goodee.mvcBoard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcBoard.service.BoardService;
import com.goodee.mvcBoard.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/boardOne")
	public String boardOne(Model model,Board board) {
		Board resultBoard = boardService.getBoardOne(board);
		model.addAttribute("board",resultBoard);
		return "/board/boardOne";
	}
	
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, Board board) {
		Board resultBoard = boardService.getBoardOne(board);
		model.addAttribute("board",resultBoard);
		return "/board/modifyBoard";
	}
	
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modifyBoard(board);
		log.debug("\u001B[45m"+ row +"\u001B[0m");
		return "redirect:/board/boardOne?boardNo="+board.getBoardNo();
	}
	
	@GetMapping("/board/removeBoard")
	public String removeBoard() {
		return "/board/removeBoard";
	}
	
	@PostMapping("board/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		log.debug("\u001B[45m"+ row +"\u001B[0m");
		return "redirect:/board/boardList";
	}
	
	
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) {
		int row = boardService.addBoard(board);
		log.debug("\u001B[45m"+ row +"\u001B[0m");
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							// 형변환을 따로 안해도 된다. null값이 넘어오면 defaultValue값으로 저장
							@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, 
							@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(name = "localName", required = false) String localName) {
		
		log.debug("\u001B[45m"+ localName +"\u001B[0m");
		
		Map<String, Object> resultMap= boardService.getBoardList(currentPage, rowPerPage, localName);
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("localNameList",resultMap.get("localNameList")); // request.setAttribute() 와 비슷한 역할
		model.addAttribute("boardList",resultMap.get("boardList"));
		
		model.addAttribute("lastPage",resultMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		
		return "/board/boardList";
	}
}
