package com.kh.start.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Map<String, String> login(MemberDTO member) {
		
		// 로그인 구현
		// 1. 유효성 검증(아이디 / 비밀번호 값이 들어왔는가, 영어숫자인가, 글자수가 괜찮은가) -> @Valid로 대체
		
		// 2. 아이디가 BOOT_MEMBER테이블에 MEMBER_ID컬럼에 존재하는 아이디인가
		// 3. 조회를 해온 비밀번호 컬럼의 암호문이 사용자가 입력한 평문으로 만들어진것이 맞는가
		
		// 사용자 인증
		Authentication auth = null;
		try {
			 auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getMemberPwd()));
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 확인하세요.");
		}
		
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		log.info("로그인성공 !");
		log.info("인증에 성공한 사용자의 정보 : {}", user);
		
		//------------------------------------------------------------------------------------
		
		// 토큰 발급
		// JWT라이브러리를 이용해서
		// AccessToken이랑 RefreshToken을 만들어서 발급
		
		Map<String, String> loginResponse = tokenService.generateToken(user.getUsername());
		loginResponse.put("memberId", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());
		loginResponse.put("role", user.getAuthorities().toString());
		
		return loginResponse;
	}

}
