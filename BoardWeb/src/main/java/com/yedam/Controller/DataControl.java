package com.yedam.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class DataControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		String json = "{\"name\" : \"홍길동\", \"age\" : 21}"; // {"name" : "홍길동", "age" : 20}
		MemberDAO mdao = new MemberDAO();
		List<MemberVO> members = mdao.members();
		json = "[";
		for (int i = 0; i < members.size(); i++) {
			json += "{\"memberId\":\""+members.get(i).getMemberId()
					+"\", \"passwd\":\"" + members.get(i).getPasswd()
					+"\", \"memberName\":\"" + members.get(i).getMemberName()
					+"\", \"responsibility\":\"" + members.get(i).getResponsibility()
					+"\"}";
			if(i + 1 < members.size()) {
				json += ",";
			}
		}
		json += "]";
		resp.getWriter().print(json);
	}

}
