package com.kh.start.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.service.BoardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	// 게시글 작성 + 첨부파일이 있는
	// @AuthenticationPrincipal로 Authentication의 principal에 담아놓은 UserDetails를 매개변수로 받을 수 있음
	// ※ 권장방법임
	@PostMapping
	public ResponseEntity<?> save(@Valid BoardDTO board,
		   @RequestParam(name="file", required=false) MultipartFile file,
		   @AuthenticationPrincipal CustomUserDetails userDetails){
		
		//log.info("게시글정보 : {}, 파일정보 : {}", board, file.getOriginalFilename());
		//log.info("이게뭔데 : {}", userDetails.getUsername());
		boardService.save(board, file, userDetails.getUsername());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	// 전체 조회
	// Get boards
	@GetMapping
	public ResponseEntity<List<BoardDTO>> findAll(@RequestParam(name="page", 
																defaultValue="0") int pageNo){
		List<BoardDTO> boards = boardService.findAll(pageNo);
		return ResponseEntity.ok(boards);
	}
	
	// 단일조회
	// GET /boards/PrimaryKey
	@GetMapping("/{boardNo}")
	public ResponseEntity<BoardDTO> findByBoardNo(@PathVariable(name="boardNo") 
												  @Min(value=1, message="넘작아용") Long boardNo){
		BoardDTO board = boardService.findByBoardNo(boardNo);
		return ResponseEntity.ok(board);
	}
	
	
	@PutMapping("/{boardNo}")
	public ResponseEntity<BoardDTO> update(@PathVariable(name="boardNo") Long boardNo,
										   BoardDTO board,
										   @RequestParam(name="file", required=false)
										   MultipartFile file,
										   @AuthenticationPrincipal CustomUserDetails userDetails){
		BoardDTO b = boardService.update(board, file, boardNo, userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<?> deleteByBoardNo(@PathVariable(name="boardNo") Long boardNo,
											 @AuthenticationPrincipal CustomUserDetails userDetails){
		boardService.deleteByBoardNo(boardNo, userDetails);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	

}
