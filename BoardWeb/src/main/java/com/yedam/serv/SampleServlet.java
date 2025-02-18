package com.yedam.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.Employee;

/*
 * 서블릿 = (http 포로토콜을 활용해서 클라이언트 쪽에 웹브라우저를 출력해줌)
 * 서블릿 생성
 * 1. HttpServlet를 상속받아야함.
 * 2. WebApplicationServer = 줄임말로(WAS) => tomcat.
 * 3. WAS의 실행 1)init메소드를 먼저 읽어드림. 2)service(기능) 3)destroy
 * 
 * servlet 실행해보는중.
 */
public class SampleServlet extends HttpServlet{
	
	// init 자동완성으로 작성
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출. 최초 한번만 실행.");
	}
	// 요청정보를 담고있는것 req 반대로 
	// service 자동완성 servlet
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 호출. 호출마다 실행.");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter(); // 출력 스트림을 생성해서 클라이언트쪽에 출력. PrintWriter(2바이트씩)
		
		EmpDAO edao = new EmpDAO();
		List<Employee> list = edao.search(new Employee());
		
		for(Employee emp : list) {
			out.print("<p>사번: <a href='empInfo?eno="+emp.getEmpNo()+"'>" + emp.getEmpNo() + "</a>, 이름: " + emp.getEmpName() + "</p>");
		}
		
	}
	
	//destroy 자동완성
	@Override
	public void destroy() {
		System.out.println("서버가 종료됩니다.");
	}
	
}
