package com.kh.start.comment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.board.model.service.BoardService;
import com.kh.start.comment.model.dao.CommentMapper;
import com.kh.start.comment.model.dto.CommentDTO;
import com.kh.start.comment.model.vo.CommentVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final BoardService boardService;
	private final CommentMapper commentMapper;

	@Override
	public CommentVO save(CommentDTO comment, CustomUserDetails userDetails) {
		
		boardService.findByBoardNo(comment.getRefBoardNo()); // 외부에 노출된 메소드 호출
		String memberId = userDetails.getUsername();
		
		CommentVO c = CommentVO.builder()
							   .commentWriter(memberId)
							   .commentContent(comment.getCommentContent())
							   .refBoardNo(comment.getRefBoardNo())
							   .build();
		commentMapper.save(c);
		return c;
	}

	@Override
	public List<CommentDTO> findAll(Long boardNo) {
		boardService.findByBoardNo(boardNo);
		return commentMapper.findAll(boardNo);
	}

}
