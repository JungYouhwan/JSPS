package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.Controller.AddBoardControl;
import com.yedam.Controller.BoardListControl;
import com.yedam.Controller.Control;

/*
 * MVC 패턴에서의 Control역활.
 * url요청 -> 서블릿.
 * 
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet{
	// Map<String, Control> map; Map<String, 인터페이스명> map
	Map<String, Control> map;
	
	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화.
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
//		map.put("url", "servlet"); // addStudent.do 요청이 들어오면 AddStudentServlet
		map.put("/boardList.do", new BoardListControl());
		map.put("/addBoard.do", new AddBoardControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("front control");
		//  http://localhost:8080/BoardWeb/addStudent.do = url
		//  /BoardWeb/addStudent.do => uri
		String uri = req.getRequestURI(); // "/BoardWeb/addStudent.do"
		String context = req.getContextPath(); // "/BoardWeb"
		// substring= 정해진 문자열에서부터 자름. uri - context = page (.do)나옴 
		String page = uri.substring(context.length());
		
		System.out.println(page);
		// page에는 /주소.do가 반환됨.
		// map컬렉션에서 key를 입력하면 val반환.
		Control control = map.get(page);
		control.exec(req, resp);
	}
}
