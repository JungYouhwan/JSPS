package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ?bno=22&title=등록&content=연습합니다.
		// 전달받은 파라메타 값을 각 변수에 저장
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// 변수에 저장된 값을 boardVO를 통해서 board 라는 변수에 저장.
		BoardVO board = new BoardVO();
		SqlSession sqlsession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlsession.getMapper(BoardMapper.class);
		
		board.setBoardNo(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		
//		BoardDAO bdao = new BoardDAO();
		// 전달받은 파라메타값을 저장한 board를 전송.
		if(mapper.updateBoard(board) > 0) {
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("처리 실패");
		}
	}

}
