package com.kh.start.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class MemberVO {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String role;
}
