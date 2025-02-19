<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>수정 화면(modifyBoard.jsp)</h3>
<%
BoardVO board = (BoardVO) request.getAttribute("board");
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
			<input type="text" class="form-control" value="<%=board.getTitle() %>">
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
			<textarea cols="45" rows="3" class="form-control"><%=board.getContent() %></textarea>
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
				<button class="btn btn-warning" type="submit">수정</button>
				<button class="btn btn-danger" type="button">삭제</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>