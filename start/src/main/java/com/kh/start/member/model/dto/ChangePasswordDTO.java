package com.kh.start.member.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangePasswordDTO {
	@Size(min = 4, max = 20, message="비밀번호는 4 ~ 20자 사이로 입력해주셈!")
	private String currentPassword;
	@Size(min = 4, max = 20, message="비밀번호는 4 ~ 20자 사이로 입력해주셈!")
	private String newPassword;

}
