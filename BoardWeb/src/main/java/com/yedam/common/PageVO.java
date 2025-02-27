package com.yedam.common;

import lombok.Data;

@Data
public class PageVO {
	
	// 67건의 데이터가 있다면. 1~14페이지.
	// 현재페이지 : 2페이지. 1 ~ 10번
	private int startPage; // 첫페이지.
	private int endPage; // 현재페이지의 마지막 페이지.
	private int currentPage; // 현재 페이지.
	private boolean prev; // 
	private boolean next; // 
	
	public PageVO(int page, int totalCnt) {
		
		currentPage = page;
		endPage = (int) Math.ceil(currentPage /10.0) * 10; // double타입 Math.ceil로 올림해서 1 * 10
		startPage = endPage - 9; // 계산상의 start, end
		
		int realEnd =(int) Math.ceil(totalCnt / 5.0); // 실제 마지막 페이지.
		endPage = endPage > realEnd ? realEnd : endPage;
		
		prev = startPage == 1 ? false : true;
		next = endPage == realEnd ? false : true;
	}
}
