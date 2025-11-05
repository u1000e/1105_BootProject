package com.kh.start.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	//AuthenticationManager가 실질적으로 사용자의 정보를 조회할 때 메소드를 호출하는 클래스
	
	private final MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDTO user = mapper.loadUser(username);
		//log.info("이거 오나요 : {}", user);
		if(user == null) {
			throw new UsernameNotFoundException("그래야 흥미가 생긴데 머리도 돌고");
		}
		
		return CustomUserDetails.builder().username(user.getMemberId())
										  .password(user.getMemberPwd())
										  .memberName(user.getMemberName())
										  .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
										  .build();
	}
	
	
	
	
	
	
	

}
