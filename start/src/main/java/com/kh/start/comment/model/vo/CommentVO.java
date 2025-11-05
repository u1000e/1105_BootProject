package com.kh.start.comment.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentVO {
	private Long commentNo;
	private String commentWriter;
	private String commentContent;
	private Date createDate;
	private Long refBoardNo;
}
