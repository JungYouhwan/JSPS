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
	
	
	public void updateCount(int boardNo) {
		String sql = "update tbl_board"
				+"    set    view_cnt = view_cnt + 1"
				+"    where  board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate(); // 쿼리 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 조회(상세조회). 글번호 => 전체정보 반환.
	public BoardVO getBoard(int boardNo) {
		String sql = "select board_no, " //
				+ "          title, " // 
				+ "          content, " //
				+ "          writer, " //
				+ "          write_date, " //
				+ "          view_cnt " //
				+ "  from tbl_board " //
				+ "  where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			// 조회결과 존재하면...
			if(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriterDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
			return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
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
	// 추가 board_no는 시퀀스의 값을 넣기로함.
	public boolean insertBoard(BoardVO board) {
		String sql = "insert into tbl_board (board_no, title, content, writer) "
				+"    values(board_seq.nextval,?,?,?)";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());
			
			int r = psmt.executeUpdate(); // insert 실행.
			if(r == 1) {
				return true; // 정상 등록.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상 처리.
	}
	// 수정
	public boolean updateBoard(BoardVO board) {
		String sql = "update tbl_board"
				+"    set    title = ? "
				+",          content = ? "
				+"    where   board_no = ? ";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getBoardNo());
			
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// 삭제
	public boolean deleteBoard(int boardNo) {
		
		return false;
	}
}
