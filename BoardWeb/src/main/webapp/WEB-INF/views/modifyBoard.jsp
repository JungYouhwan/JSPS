<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>수정 화면(modifyBoard.jsp)</h3>
<%
BoardVO board = (BoardVO) request.getAttribute("board");
String msg = (String) request.getAttribute("msg");
%>
<form action="modifyBoard.do">
<input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><%=board.getBoardNo()%></td>
			<th>조회수</th>
			<td><%=board.getViewCnt()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
			<input type="text" name="title" class="form-control" value="<%=board.getTitle() %>">
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
			<textarea cols="45" name="content" rows="3" class="form-control"><%=board.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=board.getWriter()%></td>
			<th>작성일시</th>
			<td><%=board.getWriterDate()%></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input class="btn btn-warning" type="submit" value="변경">
				<input class="btn btn-danger" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>