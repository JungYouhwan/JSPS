package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {
// 글등록 addBoard.do
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3개의 파라메터 활용 db저장. 목록으로 이동.
		// Parameter("title") addFrom.jsp에서 넘어오는 파라메타값을
		// 받아서 title에 저장 (나머지도 동일)
		
		// 톰캣서버의 폴더내에 경로 지정.
		// get RealPath = getServletContext()가 webapp까지의 경로를 애기함.
		String saveDir = req.getServletContext().getRealPath("images");
				
		// 2종류의 파일타입(multipart)
		MultipartRequest mr = new MultipartRequest(
				req// 1. 요청객체
				,saveDir   // 2. 파일의 저장 경로
				,1024*1024*5 // 3. 최대 파일 크기
				,"utf-8" // 4. 인코딩 방식
				,new DefaultFileRenamePolicy() // 5. 리네임 정책.(이름을 새로 정의)
				);
		
		// 3개의 파라미터타입 활용
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String writer = req.getParameter("writer");
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("img");
		// 매개값으로 활용.
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);
		bvo.setImg(img);
		
		SqlSession sqlsession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlsession.getMapper(BoardMapper.class);
		
//		BoardDAO bdao = new BoardDAO();
		if(mapper.insertBoard(bvo) > 0) {
			// forward vs. redirect
			resp.sendRedirect("boardList.do");
			System.out.println("성공.");
		}else {
			System.out.println("실패.");
		}
		
		
	}

}
