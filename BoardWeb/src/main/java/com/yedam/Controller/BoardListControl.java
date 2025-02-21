package com.yedam.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.PageVO;
import com.yedam.vo.SearchVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("page");
		
		// page값이 null값이 들어가는경우는 에러가 생김 그래서
		// 초기값으로 1이라는 값을 줌.
		page = page == null ? "1" : page;
		
		// 검색했을때 값 넘겨주기.
		// 전송되는 파라메타값 변수에 담아주기
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		sc = sc == null ? "" : sc; // null값 처리
		kw = kw == null ? "" : kw; // null값 처리
		SearchVO search = new SearchVO(Integer.parseInt(page), sc, kw);
		
		String name = "홍길동";
		req.setAttribute("msg", name);
		
		BoardDAO edao = new BoardDAO();
		// 페이징
		int totalCnt = edao.getTotalCount(search); // 실제 건수.
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		// 페이징 처리로 인해서 매개값으로 page 값을 넘겨줘야함.
		// 검색기능 추가로 page를 searchVO에 추가해서 같이 넘김.
		List<BoardVO> list = edao.selectBoard(search);
		req.setAttribute("list", list);
		// searchCondition, keyword 전달.
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		// 요청재지정(url:boardList.do (boardList.jsp))
		// forward 페이지 요청이 들어오면 다른페이지로 요청
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
	}

}
