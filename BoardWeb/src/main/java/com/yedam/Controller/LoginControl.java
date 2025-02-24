package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("GET")) {
			// 1. 로그인 화면.
			req.getRequestDispatcher("member/login.tiles").forward(req, resp);
			
		} else if (req.getMethod().equals("POST")) {
			
			// 로그인 기능
			String id = req.getParameter("uname");
			String pw = req.getParameter("psw");

			// 로그인 체크
			MemberDAO mdao = new MemberDAO();
			MemberVO mvo = mdao.login(id, pw);
			if (mvo != null) { // 로그인 실패
				System.out.println("환영합니다.");
				// 세션객체에 로그인 아이디를 저장.
				// 세션을 직접 지우지않는 한 계속 세션에 저장되어있음.
				HttpSession session = req.getSession();
				session.setAttribute("loginId", id); // attribute활용.
				// 일반사용자 or 관리자
				if(mvo.getResponsibility().equals("Admin")) {
					resp.sendRedirect("memberList.do");	
				}else {
				resp.sendRedirect("boardList.do");
				}
			}else {
				System.out.println("id pw 확인.");
			}
		}

	}

}
