package com.kh.start.member.model.service;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.exception.IdDuplicateException;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.ChangePasswordDTO;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.MemberVO;
import com.kh.start.token.model.dao.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final TokenMapper tokenMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void signUp(MemberDTO member) {
		
		// 유효성 검사 ==> Validator에게 위임
		// 아이디 중복 검사
		int count = memberMapper.countByMemberId(member.getMemberId());
		if(1 == count) {
			throw new IdDuplicateException("이미 존재하는 아이디입니다.");
		}
		/*
		// 비밀번호 암호화
		String encodedPwd = passwordEncoder.encode(member.getMemberPwd());
		// ROLE주기
		MemberVO singUpMember = new MemberVO(member.getMemberId()
											,encodedPwd
											,member.getMemberName()
											,"ROLE_USER");
		*/
		MemberVO memberBuilder = MemberVO.builder()
										 .memberId(member.getMemberId())
										 .memberPwd(passwordEncoder.encode(member.getMemberPwd()))
										 .memberName(member.getMemberName())
										 .role("ROLE_USER")
										 .build();
		// 매퍼 호출
		memberMapper.signUp(memberBuilder);
		log.info("사용자 등록 성공 : {}", memberBuilder);
		
	}

	@Override
	public void changePassword(ChangePasswordDTO password) {
		
		// 현재 비밀번호가 맞는지 검증 ==> passwordEncoder.matches(평문, 암호문)
		// Authentication에서 현재 인증된 사용자의 정보 뽑아오기
		/*
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		String currentPassword = password.getCurrentPassword();
		String encodedPassword = user.getPassword();
		if(!passwordEncoder.matches(currentPassword, encodedPassword)) {
			throw new CustomAuthenticationException("일치하지 않는 비밀번호");
		}
		*/
		CustomUserDetails user = validatePassword(password.getCurrentPassword());
		// 현재 비밀번호가 맞다면 새 비밀번호를 암호화
		String newPassword = passwordEncoder.encode(password.getNewPassword());
		// UPDATE BOOT_MEMBER MEMBER_PWD = "newpassword" WHERE MEMBER_ID = "사용자ID"
		Map<String, String> changeRequest = Map.of("memberId", user.getUsername(),
												   "newPassword", newPassword);
		
		memberMapper.changePassword(changeRequest);
	}

	@Override
	@Transactional
	public void deleteByPassword(String password) {
		// 사용자가 입력한 비밀번호가 DB에 저장된 비밀번호 암호문이 쿵짜짝 이게 맞는지 검증
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		// 검증이 맞다면
		//if(!passwordEncoder.matches(password, user.getPassword())) {
		//	throw new CustomAuthenticationException("비밀번호가 일치하지 않습니다.");
		//}
		// DELETE FROM BOOT_MEMBER WHERE MEMBER_ID = 사용자 아이디
		CustomUserDetails user = validatePassword(password);
		tokenMapper.deleteToken(user.getUsername());
		memberMapper.deleteByPassword(user.getUsername());
	}
	
	private CustomUserDetails validatePassword(String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		// 검증이 맞다면
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new CustomAuthenticationException("비밀번호가 일치하지 않습니다.");
		}
		return user;
	}
	
	

}
