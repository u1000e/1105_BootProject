package com.kh.start.member.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

	@Insert("INSERT INTO BOOT_MEMBER VALUES(#{memberId}, #{memberPwd}, #{memberName}, #{role})")
	int signUp(MemberVO member);
	
	@Select("SELECT COUNT(*) FROM BOOT_MEMBER WHERE MEMBER_ID = #{memberId}")
	int countByMemberId(String memberId);
	
	@Select("""
				SELECT
				       MEMBER_ID memberId
				     , MEMBER_PWD memberPwd
				     , MEMBER_NAME memberName
				     , role
				  FROM
				       BOOT_MEMBER
				 WHERE
				       MEMBER_ID = #{memberId}
			""")
	MemberDTO loadUser(String memberId);
	
	@Update("UPDATE BOOT_MEMBER SET MEMBER_PWD = #{newPassword} WHERE MEMBER_ID = #{memberId}")
	void changePassword(Map<String, String> changeRequest);
	
	
	@Delete("DELETE FROM BOOT_MEMBER WHERE MEMBER_ID = #{memberId}")
	void deleteByPassword(String memberId);
	
	
	
	
}
