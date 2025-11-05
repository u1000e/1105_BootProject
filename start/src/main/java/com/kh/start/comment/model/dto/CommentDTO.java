package com.kh.start.comment.model.dto;

import java.sql.Date;

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
public class CommentDTO {
	private Long commentNo;
	private String commentWriter;
	private String commentContent;
	private Date createDate;
	private Long refBoardNo;
}








