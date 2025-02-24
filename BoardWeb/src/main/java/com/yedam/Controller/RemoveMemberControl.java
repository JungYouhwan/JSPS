package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("mid");

		// MemberDAO에 삭제. boolean deleteMember(String id);
		boolean isOk = true;
		
		if(isOk) {
			// {"retCode" : "OK"}
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			// {"retCode" : "NG"}
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
		}
	}

}
