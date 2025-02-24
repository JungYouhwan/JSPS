<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>수정 화면</h3>
<form action="modifyBoard.do">
<input type="hidden" name="bno" value="${board.boardNo }">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><c:out value="${board.boardNo }" /></td>
			<th>조회수</th>
			<td><c:out value="${board.viewCnt }" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
			<input type="text" name="title" class="form-control" value="${board.title }">
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
			<textarea cols="45" name="content" rows="3" class="form-control"><c:out value="${board.content }" /></textarea>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${board.writer }" /></td>
			<th>작성일시</th>
			<td><c:out value="${board.writeDate }" /></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input class="btn btn-warning" type="submit" value="변경">
				<input class="btn btn-danger" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
