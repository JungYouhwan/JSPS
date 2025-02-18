package com.yedam.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;

/*
 * 추가, 수정, 삭제, 조회
 * create, Read, Update, Delete
 *  
 */
public class BoardDAO extends DAO{
	
	// 조회(삭제)
	public List<BoardVO> selectBoard() {
		List<BoardVO> boardlist = new ArrayList<>();
		String qry = "select board_no, "
				+ "          title, "
				+ "          content, "
				+ "          writer, "
				+ "          write_date, "
				+ "          view_cnt "
				+ "from tbl_board";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = getConnect().prepareStatement(qry);
			rs = stmt.executeQuery(qry);
			// 조회결과 저장
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriterDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardlist;
	}
	// 추가
	public boolean insertBoard(BoardVO board) {
		return false;
	}
	// 수정
	public boolean updateBoard(BoardVO board) {
		return false;
	}
	// 삭제
	public boolean deleteBoard(int boardNo) {
		return false;
	}
}
