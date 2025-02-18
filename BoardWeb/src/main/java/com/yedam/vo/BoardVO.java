package com.yedam.vo;

import java.util.Date;

public class BoardVO {
	private int boardNo; // boat
	private String title;
	private String content;
	private String writer;
	private Date writerDate;
	private int viewCnt;
	
	
	

	public BoardVO(int boardNo, String title, String content, String writer, Date writerDate, int viewCnt) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writerDate = writerDate;
		this.viewCnt = viewCnt;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writerDate=" + writerDate + ", viewCnt=" + viewCnt + "]";
	}

	// title......view_cnt
	public BoardVO() {
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWriterDate() {
		return writerDate;
	}

	public void setWriterDate(Date writerDate) {
		this.writerDate = writerDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	
}
