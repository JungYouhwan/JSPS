package com.yedam.serv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.StudentDAO;
import com.yedam.vo.Student;

@WebServlet("/addStd")
public class AddStudentServlet extends HttpServlet{
	// param의 값을 활용. -> db입력
	// 처리성공 / 처리실패 메세지.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // post방식의 한글 인코딩처리
		resp.setContentType("text/htmk;charset=utf-8");
		// ?std_no=S230&std_name=정유환&tel_no=123&std_addr=1234
		String stdno = req.getParameter("std_no");
		String stdname = req.getParameter("std_name");
		String telno = req.getParameter("tel_no");
		String stdaddr = req.getParameter("std_addr");
		// 
		StudentDAO stddao = new StudentDAO();
		
		boolean result = stddao.addStudent(new Student(stdno, stdname, telno, stdaddr));

		
		if (result) {
			resp.getWriter().print("처리성공");
		} else {
			resp.getWriter().print("처리실패");
		}
	}
}
