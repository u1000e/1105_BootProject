package com.kh.start.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	void save(BoardVO board);
	
	List<BoardDTO> findAll(RowBounds rb);
	
	BoardDTO findByBoardNo(Long boardNo);
	
	void update(BoardDTO board);
	
	void deleteByBoardNo(Long boardNo);

}
