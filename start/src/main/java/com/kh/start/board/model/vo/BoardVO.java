package com.kh.start.board.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BoardVO {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String fileUrl;
	private String status;
	private Date createDate;
}
