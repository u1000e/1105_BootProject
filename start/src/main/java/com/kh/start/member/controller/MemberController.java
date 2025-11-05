package com.kh.start.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.member.model.dto.ChangePasswordDTO;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	//	
	//	@Autowired
	//	public MemberController(MemberService memberService) {
	//		this.memberService = memberService;
	//	}
	//	
	// 회원가입 => 일반회원 => ROLE컬럼에 들어갈 값 필드에 담아주어야함
	//				   => 비밀번호 암호화
	//				   => VO에 담을것
	// VO : ValueObject(값을 담는 역할) ==> 불변해야한다는것이 특징
	// DTO : DataTransferObject(데이터 전송)
	
	// GET
	// GET(/members/멤버번호)
	// POST
	// PUT
	// DELETE
	
	// 로그인은 여기다가 안만들것
	@PostMapping
	public ResponseEntity<?> signUp(@Valid @RequestBody MemberDTO member){
		log.info("멤버 잘들어오나 : {}", member);
		memberService.signUp(member);
		return ResponseEntity.status(201).build();
	}
	
	// 비밀번호 변경 기능 구현
	@PutMapping
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO password){
		// 1번 비밀번호 입력값에 대한 유효성 검증
		log.info("비밀번호 정보 : {}", password);
		// 2번 지금 요청을 보낸 사용자가 입력한 기존의 비밀번호가 잘 맞는지 확인
		// 3번 새로 입력한 비밀번호에 대한 암호화 작업
		// 4번 새 비밀번호로 변경
		memberService.changePassword(password);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteByPassword(@RequestBody Map<String, String> request){
		log.info("이게오나 ? {}", request);
		memberService.deleteByPassword(request.get("password"));
		return ResponseEntity.ok("옥까이~");
	}
	
	
	
	
	
	
	
	

}
