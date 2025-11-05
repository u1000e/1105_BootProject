package com.kh.start.auth.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.auth.model.service.AuthService;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody MemberDTO member){
		Map<String, String> loginResponse = authService.login(member);
		return ResponseEntity.ok(loginResponse);
	}
	
	
	
	// 로그인방식 => 웹 애플리케이션은 대부분 인증과정이 포함됨
	//
	// 세션 기반 로그인방식
	// 토큰 기반 로그인방식 => JsonWebToken
	
	// JWT 프로세스
	
	// 1. 사용자 인증(로그인)
	// 아이디 비밀번호를 입력해서 로그인할거임 -> 성공하면 JWT를 만들어줄거임 -> 응답데이터에 JWT 보내줄거임
	
	// 2. 클라이언트 입장(브라우저)
	// 클라이언트는 응답받은 JWT토큰을 저장소에 보관할거임
	
	// 3. 클라이언트 입장(토큰 발급 이후)
	// 요청에 JWT를 헤더에 포함시켜서 서버에 전송
	
	// 4. 서버 입장(토큰 검증)
	// 클라이언트가 보내준 JWT의 서명을 검증하고 토큰의 유효성검사
	// 유효성검사를 통과하면 클레임을 까봐서 권한체크를 하고 요청 처리
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody Map<String, String> token){
		String refreshToken = token.get("refreshToken");
		Map<String, String> tokens = tokenService.validateToken(refreshToken);
		return ResponseEntity.status(HttpStatus.CREATED).body(tokens);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
