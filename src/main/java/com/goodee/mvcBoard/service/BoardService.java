package com.goodee.mvcBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcBoard.mapper.BoardMapper;
import com.goodee.mvcBoard.mapper.BoardfileMapper;
import com.goodee.mvcBoard.vo.Board;
import com.goodee.mvcBoard.vo.Boardfile;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName){
		
		// service layer 역할1 : controller가 넘겨준 매개값을 Dao의 매개값형태에 맞게 가공
		int beginRow = (currentPage-1)*rowPerPage;
		// 반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		
		int boardCount = boardMapper.selectBoardCount();
		// service layer 역할2 : Dao에서 반환받은 값을 가공하여 controller에 반환 
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
	}
	
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);
		
		// board입력이 성공하고 첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(row == 1 && fileList != null && fileList.size() > 0) {
			int boardNo = board.getBoardNo();
			
			for(MultipartFile mf : fileList) { // 첨부된 파일의 개수만큼 반복
				if(mf.getSize() > 0) {
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); // 부모 키값
					bf.setOriginFilename(mf.getOriginalFilename()); // 파일원본이름
					bf.setFilesize(mf.getSize()); // 파일사이즈
					bf.setFiletype(mf.getContentType()); // 파일타입(MIME)
					// 저장될 파일 이름
					// 확장자
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
					// 새로운 이름 + 확장자
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
					
					// 테이블에 저장
					boardfileMapper.insertBoardfile(bf);
					// 파일 저장(저장위치 필요 -> path변수)
					// path위치에 저장파일 이름으로 빈 파일을 생성
					File f = new File(path+bf.getSaveFilename());
					// 빈파일에 첨부된 파일의 스트림을 주입한다.
					try {
						mf.transferTo(f); // 스트림 주입 메서드
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
						// 트랜잭션 작동을 위해 예외(try...catch를 강요하지 않는 예외 
						// ex: runtimeException)의 발생이 필요하다 
						throw new RuntimeException();
					}
				}
			}
		}
		
		return row;
	}
	
	public int removeBoard(Board board, String path) {
		// 저장된 파일 삭제
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfile(board);
		if(boardfileList != null && boardfileList.size() > 0) {
			for(Boardfile bf : boardfileList) {
				File f = new File(path+bf.getSaveFilename());
				if(f.exists()) {
					f.delete();
				}
			}
			// boardfile테이블에서 파일삭제
			int boardfileRow = boardfileMapper.deleteBoardfile(board); //자식테이블인 boardfile을 먼저 삭제해야한다
		}
		// 게시글 삭제
		int boardRow = boardMapper.deleteBoard(board);
		return boardRow;
	}
	
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	public Map<String, Object> getBoardOne(Board board) {
		Board resultBoard = boardMapper.selectBoardOne(board);
		List<Boardfile> boardFileList = boardfileMapper.selectBoardfile(board);
		Map<String, Object> map = new HashMap<>();
		map.put("board", resultBoard);
		map.put("boardFileList", boardFileList);
		
		return map;
	}

}
