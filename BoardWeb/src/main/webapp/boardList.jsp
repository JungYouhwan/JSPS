<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- html주석 -->
	<h3>반복문</h3>
	<%
	String msg = "Hello";
	System.out.println(msg);
	String result = (String) request.getAttribute("msg");
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	%>
	<p>
		msg의 값은
		<%=result%>
	</p>
	<h3>게시글 목록</h3>
	<table border="2">
		<tbody>
			<%
			for (BoardVO board : list) {
			%>
			<tr>
				<td><%=board.getBoardNo() %></td>
				<td><%=board.getTitle() %></td>
				<td><%=board.getWriter() %></td>
				<td><%=board.getWriterDate() %></td>
				<td><%=board.getViewCnt() %></td>
			</tr>
			<%
			} // for 종료.
			%>
		</tbody>
	</table>
</body>
</html>