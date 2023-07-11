package com.goodee.mvcBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcBoard.vo.Board;

@Mapper
public interface BoardMapper {
	// local_name컬럼과 count()를 반환
	// 인터페이스 
	// public 생략
	List<Map<String, Object>> selectLocalNameList();
	
	// mybatis 메서드는 매개값을 하나만 허용
	// param : Map<String, Object> map --> int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object> map); 
	
	// 전체 행의 수
	int selectBoardCount();
}
