package com.yedam.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.PageVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("page");
		
		// page값이 null값이 들어가는경우는 에러가 생김 그래서
		// 초기값으로 1이라는 값을 줌.
		page = page == null ? "1" : page;
		
		String name = "홍길동";
		req.setAttribute("msg", name);
		
		BoardDAO edao = new BoardDAO();
		// 페이징
		int totalCnt = edao.getTotalCount(); // 실제 건수.
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		// 페이징 처리로 인해서 매개값으로 page 값을 넘겨줘야함.
		List<BoardVO> list = edao.selectBoard(Integer.parseInt(page));
		req.setAttribute("list", list);
		
		// 요청재지정(url:boardList.do (boardList.jsp))
		// forward 페이지 요청이 들어오면 다른페이지로 요청
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
	}

}
