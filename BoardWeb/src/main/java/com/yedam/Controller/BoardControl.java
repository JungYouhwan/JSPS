package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.BoardDAO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {
// 상세 조회
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		
//		BoardDAO bdao = new BoardDAO();
		SqlSession sqlsession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlsession.getMapper(BoardMapper.class);
		mapper.updateCount(Integer.parseInt(bno)); // 조회수 보여주기
		BoardVO board = mapper.getBoard(Integer.parseInt(bno)); // 문자열 "14
		// 요청정보의 attribute 활용.
		req.setAttribute("board", board);		
		req.getRequestDispatcher("board/board.tiles").forward(req, resp);
	}

}
