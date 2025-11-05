package com.kh.start.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value // AllArgsConstructor, Getter, ToString, equals, hashCode
@Builder
public class CustomUserDetails implements UserDetails {
	private String username; // MEMBER_ID컬럼값 담는 용도
	private String password;
	private String memberName;
	private Collection<? extends GrantedAuthority> authorities;
}
