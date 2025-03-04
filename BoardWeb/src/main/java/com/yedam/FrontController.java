package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.Controller.AddBoardControl;
import com.yedam.Controller.AddFormControl;
import com.yedam.Controller.AddMemberControl;
import com.yedam.Controller.AddReplyControl;
import com.yedam.Controller.AjaxControl;
import com.yedam.Controller.BoardControl;
import com.yedam.Controller.BoardListControl;
import com.yedam.Controller.ChartCount;
import com.yedam.Controller.ChartData;
import com.yedam.Controller.Control;
import com.yedam.Controller.DataControl;
import com.yedam.Controller.DataTableControl;
import com.yedam.Controller.LoginControl;
import com.yedam.Controller.LogoutControl;
import com.yedam.Controller.MainControl;
import com.yedam.Controller.MemberListControl;
import com.yedam.Controller.ModifyBoardControl;
import com.yedam.Controller.ModifyControl;
import com.yedam.Controller.RemoveBoardControl;
import com.yedam.Controller.RemoveMemberControl;
import com.yedam.Controller.RemoveReplyControl;
import com.yedam.Controller.ReplyCount;
import com.yedam.Controller.ReplyListControl;
import com.yedam.common.AddData;
import com.yedam.common.ApiControl;
import com.yedam.common.FullCalendarContro;
import com.yedam.common.FullData;
import com.yedam.common.MapControl;
import com.yedam.common.removeData;

/*
 * MVC 패턴에서의 Control역활.
 * url요청 -> 서블릿.
 * 
 */
//@WebServlet("*.do")
public class FrontController extends HttpServlet{
	// Map<String, Control> map; Map<String, 인터페이스명> map
	Map<String, Control> map;
	
	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화.
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
//		map.put("url", "servlet"); // addStudent.do 요청이 들어오면 AddStudentServlet
		
		map.put("/main.do", new MainControl()); // 메인 화면
		map.put("/boardList.do", new BoardListControl()); // 조회 기능.
		map.put("/addForm.do", new AddFormControl()); // 등록 화면.
		map.put("/addBoard.do", new AddBoardControl()); // 등록 기능.
		map.put("/board.do", new BoardControl()); // 상세 화면
		map.put("/modifyForm.do", new ModifyControl()); // 수정 화면
		map.put("/modifyBoard.do", new ModifyBoardControl()); // 수정 기능
		map.put("/removeBoard.do", new RemoveBoardControl()); // 삭제 기능
		
		map.put("/loginForm.do", new LoginControl()); // 로그인화면
		map.put("/login.do", new LoginControl()); // 로그인 기능
		map.put("/logout.do", new LogoutControl()); // 로그아웃 기능	
		
		map.put("/memberList.do", new MemberListControl());
		map.put("/testAjax.do", new AjaxControl()); // ajax 시작
		map.put("/testData.do", new DataControl());
		// 회원삭제.
		map.put("/removeMember.do", new RemoveMemberControl());
		// 회원 등록.
		map.put("/addMember.do", new AddMemberControl());
		// 댓글관련.
		map.put("/replyList.do", new ReplyListControl());
		map.put("/addReply.do",  new AddReplyControl());	
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/getReplyCnt.do", new ReplyCount());
		// 차트.
		map.put("/chart.do", new ChartCount());
		map.put("/chartData.do", new ChartData());
		// API
		map.put("/datatable.do", new DataTableControl());
		// FullCalander
		map.put("/full.do", new FullCalendarContro());
		map.put("/fullData.do", new FullData()); // 조회.
		map.put("/addData.do", new AddData()); // 등록.
		map.put("/remove.do", new removeData()); // 삭제.
		// 공공데이터
		map.put("/api.do", new ApiControl()); // 공공데이터 활용.
		map.put("/map.do", new MapControl()); // 카카오맵으로 보이기
		}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//  http://localhost:8080/BoardWeb/addStudent.do = url
		//  /BoardWeb/addStudent.do => uri
		String uri = req.getRequestURI(); // "/BoardWeb/addStudent.do"
		String context = req.getContextPath(); // "/BoardWeb"
		// substring= 정해진 문자열에서부터 자름. uri - context = page (.do)나옴 
		String page = uri.substring(context.length());
		
		// page에는 /주소.do가 반환됨.
		// map컬렉션에서 key를 입력하면 val반환.
		Control control = map.get(page);
		control.exec(req, resp);
	}
}
