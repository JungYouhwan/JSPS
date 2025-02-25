package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");
		String name = req.getParameter("mname");
		
		MemberDAO mdao = new MemberDAO();
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setPasswd(pw);
		member.setMemberName(name);
		
		// 추가메소드(insertMember(MemberVO member)
		boolean isOk = mdao.updateMember(member);
		
		// 처리결과 반환
		if(isOk) {
			// {"retCode" : "OK"}
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			// {"retCode" : "NG"}
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
		}
	}

}
