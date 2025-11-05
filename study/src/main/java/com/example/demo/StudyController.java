package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("study")
// SpringWebStater는 기본적으로 JSON형태로 Converting해줄 수 있는 Jackson라이브러리 포함
// produces 기본값이 application/json; charset=UTF-8이기 때문에
// 자동으로 MessageConverter로 변환됨 잘 응답됨
public class StudyController {
	/*
	 * Spring Stater
	 * 
	 * 특정 기능에 필요한 의존성 라이브러리들을 한 번에 관리할 수 있는 개념
	 * 
	 * 각각의 Stater는 관련된 라이브러리들의 집합으로 모든 의존성을 하나의 Stater로 관리하고
	 * 쉽게 추가할 수 있음
	 * 
	 * 예)
	 * spring-boot-starter-web : 웹 애플리케이션 개발에 필요한 의존성들이 모여있음
	 * (Servlet, DispatcherServlet, MVC, Jackson 등)
	 * spring-boot-starter-security : 스프링 시큐리티(보안)관련된 의존성들이 모여있음
	 * 
	 * 개발자는 필요한 기능이 있다 => Stater추가해야지 => 의존성관리를 직접하지 않음
	 * 모든 개발자가 동일한 Stater를 쓰기 때문에 프로젝트 간 의존성 충돌도 방지할 수 있음
	 * 
	 * ※ Starter에 모든 라이브러리가 존재하는 것은 아님
	 */
	
	@Autowired
	private StudyBean studyBean;
	
	@GetMapping
	public ResponseEntity<String> getTest(){
		return ResponseEntity.ok("응답 잘갑니다용~");
	}
	
	/*
	 * Spring Boot 썼을 때 장점?
	 * 
	 * ==> 개발 생산성 향상
	 * 
	 */
	

}
