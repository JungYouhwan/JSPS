package com.yedam.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.Employee;

// 창을 띄울 주소
@WebServlet("/empInfo")
public class GetEmployeeServ extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		// 파라미터(?eno=1001)
		// 전송된 파라미터값을 eno변수에 저장.
		String eno = req.getParameter("eno");

		EmpDAO edao = new EmpDAO();
		Employee result = edao.selectEmp(Integer.parseInt(eno));
		String str= "<table border='2'>"; // <table><tr><th>사번</th><td>...
		str += "<tr><th>사번</th><td>" + result.getEmpNo() + "</td></tr>";
		str += "<tr><th>전화</th><td>" + result.getTelNo() + "</td></tr>";
		str += "</table>";
		str += "<a href='sample'>목록이동</a>";
		// PrintWriter의 writer = 출력스트림.
		PrintWriter out = resp.getWriter();
		out.print(str);
	}
}
