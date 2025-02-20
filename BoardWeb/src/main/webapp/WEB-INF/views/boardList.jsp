<%@page import="com.yedam.vo.PageVO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<!-- html주석 -->
<h3>반복문</h3>
<%
String msg = "Hello";
String result = (String) request.getAttribute("msg");
List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
// Control에서 paging의 값을 얻어오기.
PageVO paging = (PageVO) request.getAttribute("paging");
%>
<p>
	page의 값은
	<%=paging%>
</p>
<h3>게시글 목록</h3>
<table class="table table-striped">
	<thead>
		<tr class="table-dark">
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (BoardVO board : list) {
		%>
		<tr>
			<td><%=board.getBoardNo()%></td>
			<td><a href="board.do?bno=<%=board.getBoardNo()%>"><%=board.getTitle()%></a></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getWriterDate()%></td>
			<td><%=board.getViewCnt()%></td>
		</tr>
		<%
		} // for 종료.
		%>
	</tbody>
</table>
<!-- paging 시작. -->
<nav aria-label="...">
	<ul class="pagination">
	<!-- 이전페이지 여부 -->
	<%if (paging.isPrev()) { %>
	<li class="page-item"><a class="page-link" href="boardList.do?page=<%=paging.getEndPage() - 1 %>">Previous</a>
		</li>
		<%} else { %>
		<li class="page-item disabled"><span class="page-link">Previous</span>
		</li>
		<%} %>
		<li class="page-item"><a class="page-link" href="boardList.do?page=1"><<</a>
		</li>
		<%for(int p = paging.getStartPage(); p<= paging.getEndPage(); p++){ %>
		<%if (p == paging.getCurrentPage()) { %>
		<li class="page-item active"><span class="page-link"><%=p %></span></li>
			<%} else { %>
			<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=p %>"><%=p %></a></li>
			<%}%>
		<%} %>
			<li class="page-item"><a class="page-link" href="boardList.do?page=<%=paging.getEndPage() + 1 %>">>></a></li>
	</ul>
</nav>
<!-- paging 끝. -->
<jsp:include page="includes/footer.jsp"></jsp:include>