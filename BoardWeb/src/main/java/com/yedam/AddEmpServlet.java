package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 최초호출 - 실행 - 종료
// init - service - destroy : 서블릿의 생명주기.
// @WebServlet(해당주소입력)
@WebServlet("/addEmployee.html")
public class AddEmpServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form태그의 input값에 입력하여 전송하면 나오는값. ?empNo=1004&empName=김태식&telNo=654-0107
		resp.setContentType("text/html;charset=utf-8");
		String eno = req.getParameter("empNo"); // empNo의 param에 담겨있는 값 반환.
		String ename = req.getParameter("empName");
		String tel = req.getParameter("telNo");

		// db등록.
		EmpDAO edao = new EmpDAO();

		boolean result = edao.registerEmp(new Employee(Integer.parseInt(eno), ename, tel));
		if (result) {
			resp.sendRedirect("sample"); // addEmpServle -> sample 페이지 이동.
		} else {
			resp.getWriter().print("처리실패");
		}
	}
}
