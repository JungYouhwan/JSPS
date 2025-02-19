package com.yedam.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private int boardNo; // boat
	private String title;
	private String content;
	private String writer;
	private Date writerDate;
	private int viewCnt;
	
}
