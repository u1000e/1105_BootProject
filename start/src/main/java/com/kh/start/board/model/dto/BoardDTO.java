package com.kh.start.board.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
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
public class BoardDTO {
	
	private Long boardNo;
	@NotBlank
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String fileUrl;
	private String status;
	private Date createDate;

}
