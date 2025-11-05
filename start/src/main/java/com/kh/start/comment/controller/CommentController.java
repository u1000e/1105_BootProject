package com.kh.start.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.comment.model.dto.CommentDTO;
import com.kh.start.comment.model.service.CommentService;
import com.kh.start.comment.model.vo.CommentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<CommentVO> save(@RequestBody CommentDTO comment,
								  @AuthenticationPrincipal CustomUserDetails userDetails){
		CommentVO c = commentService.save(comment, userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll(@RequestParam(name="boardNo") Long boardNo){
		return ResponseEntity.ok(commentService.findAll(boardNo));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
