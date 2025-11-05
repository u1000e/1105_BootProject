package com.kh.start.auth.model.service;

import java.util.Map;

import com.kh.start.member.model.dto.MemberDTO;

public interface AuthService {
	
	Map<String, String> login(MemberDTO member);
	

}
