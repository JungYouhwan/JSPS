package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {
// 글등록 addBoard.do
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3개의 파라메터 활용 db저장. 목록으로 이동.
		// Parameter("title") addFrom.jsp에서 넘어오는 파라메타값을
		// 받아서 title에 저장 (나머지도 동일)
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		
		// 매개값으로 활용.
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);
		
		BoardDAO bdao = new BoardDAO();
		if(bdao.insertBoard(bvo)) {
			// forward vs. redirect
			resp.sendRedirect("boardList.do");
			System.out.println("성공.");
		}else {
			System.out.println("실패.");
		}
		
		
	}

}
