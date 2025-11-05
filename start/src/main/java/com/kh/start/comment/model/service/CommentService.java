package com.kh.start.comment.model.service;

import java.util.List;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.comment.model.dto.CommentDTO;
import com.kh.start.comment.model.vo.CommentVO;

public interface CommentService {
	
	// 인서트 하나
	CommentVO save(CommentDTO comment, CustomUserDetails userDetails);
	
	// 조회 하나
	List<CommentDTO> findAll(Long boardNo);

}
