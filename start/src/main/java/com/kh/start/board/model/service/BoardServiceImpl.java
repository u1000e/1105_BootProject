package com.kh.start.board.model.service;

import java.security.InvalidParameterException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.board.model.dao.BoardMapper;
import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.vo.BoardVO;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;

	@Override
	public void save(BoardDTO board, MultipartFile file, String username) {
		
		// 유효성 검증 valid로 퉁
		// 권한검증 -> ROLE로함
		BoardVO b = null;
		// 첨부파일 관련 값
		if(file != null && !file.isEmpty()) {
			// 이름 바꾸기~
			// 원본파일명에서 확장자 뽑기~
			// 저장위치 정해야함~
			// 파일 올리는 메소드 호출~
			String filePath = fileService.store(file);
			b = BoardVO.builder()
							   .boardTitle(board.getBoardTitle())
							   .boardContent(board.getBoardContent())
							   .boardWriter(username)
							   .fileUrl(filePath)
							   .build();
		// title, content, writer, file INSERT
		} else {
			b = BoardVO.builder()
							   .boardTitle(board.getBoardTitle())
							   .boardContent(board.getBoardContent())
							   .boardWriter(username)
							   .build();
		}
		boardMapper.save(b);
	}

	@Override
	public List<BoardDTO> findAll(int pageNo) {
		if(pageNo < 0) {
			throw new InvalidParameterException("유효하지않은 접근입니다.");
		}
		RowBounds rb = new RowBounds(pageNo * 3, 3);
		return boardMapper.findAll(rb);
	}

	@Override
	public BoardDTO findByBoardNo(Long boardNo) {
		return getBoardOrThrow(boardNo);
	}
	
	private BoardDTO getBoardOrThrow(Long boardNo) {
		BoardDTO board = boardMapper.findByBoardNo(boardNo);
		if(board == null) {
			throw new InvalidParameterException("유효하지 않은 접근입니다.");
		}
		return board;
	}
	
	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file
						  ,Long boardNo, CustomUserDetails userDetails) {
		
		// 1. 게시글번호가 존재하는 게시글인가
		// 2. 현재 토큰 소유주가 게시글작성자와 일치하는가
		// 3. 새로운 파일이 첨부되었는가
		// 4. 만약 첨부되었다면 새롭게 파일을 업로드 한 후 새로운 패스로 변경
		// 5. 젠부 옥까이다 그러면 UPDATE
		/*
		BoardDTO b = getBoardOrThrow(boardNo);
		if(!b.getBoardWriter().equals(userDetails.getUsername())) {
			throw new CustomAuthenticationException("게시글 작성자가 아닙니다.");
		}
		*/
		validateBoard(boardNo, userDetails);
		board.setBoardNo(boardNo);
		if(file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			board.setFileUrl(filePath);
		}
		boardMapper.update(board);
		return board;
	}
	
	private void validateBoard(Long boardNo, CustomUserDetails userDetails) {
		BoardDTO board = getBoardOrThrow(boardNo);
		if(!board.getBoardWriter().equals(userDetails.getUsername())) {
			throw new CustomAuthenticationException("게시글 작성자가 아닙니다.");
		}
	}
	
	@Override
	public void deleteByBoardNo(Long boardNo, CustomUserDetails userDetails) {
		/*
		BoardDTO board = getBoardOrThrow(boardNo);
		if(!board.getBoardWriter().equals(userDetails.getUsername())) {
			throw new CustomAuthenticationException("게시글 작성자가 아닙니다.");
		}
		*/
		validateBoard(boardNo, userDetails);
		boardMapper.deleteByBoardNo(boardNo);
	}

}
