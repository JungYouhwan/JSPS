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

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ?bno=22
		String bno = req.getParameter("bno");
//		BoardDAO bdao = new BoardDAO();
		
		SqlSession sqlsession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlsession.getMapper(BoardMapper.class);
		
		
		if(mapper.deleteBoard(Integer.parseInt(bno)) == 1) {
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("처리실패.");
		}
		

	}

}
