package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션을 지우기 위해서 기존에 세션값을 session에 담음.
		HttpSession session = req.getSession(); // jsessionid쿠키.
		session.invalidate();
		
		resp.sendRedirect("main.do");
	}

}
