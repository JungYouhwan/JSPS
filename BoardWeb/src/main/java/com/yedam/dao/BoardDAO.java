package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;
import com.yedam.vo.SearchVO;

/*
 * 추가, 수정, 삭제, 조회
 * create, Read, Update, Delete
 *  
 */
public class BoardDAO extends DAO {

	public int getTotalCount(SearchVO search) {
		String qry = "select count(1) from tbl_board";
		if(search.getSearchCondition().equals("T")) {
			qry += "             where title like '%'||?||'%' "; 
		} else if(search.getSearchCondition().equals("W")){
			qry += "             where writer like '%'||?||'%' ";
		} else if(search.getSearchCondition().equals("TW")){
			qry += "             where title like '%'||?||'%' or writer like '%'||?||'%' ";
		}
		
		try {
			int cnt =1;
			psmt = getConnect().prepareStatement(qry);
			if(search.getSearchCondition().equals("T")) { // 제목만 검색
				psmt.setString(cnt++, search.getKeyword());
			} else if(search.getSearchCondition().equals("W")){ // 작성자만 검색
				psmt.setString(cnt++, search.getKeyword());
			} else if(search.getSearchCondition().equals("TW")){ // 제목 + 작성자 검색
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
			}
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1); // count(1) 값.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return 0; // 조회정보 없음.
	}

	public void updateCount(int boardNo) {
		String sql = "update tbl_board" + "    set    view_cnt = view_cnt + 1" + "    where  board_no = ?";

		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate(); // 쿼리 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.
		}
	}

	// 조회(상세조회). 글번호 => 전체정보 반환.
	public BoardVO getBoard(int boardNo) {
		String sql = "select board_no, " //
				+ "          title, " //
				+ "          content, " //
				+ "          writer, " //
				+ "          write_date, " //
				+ "          view_cnt, " //
				+ "          img " //
				+ "  from tbl_board " //
				+ "  where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			// 조회결과 존재하면...
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setImg(rs.getString("img"));
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return null;
	}

	// 조회(삭제)
	public List<BoardVO> selectBoard(SearchVO search) {
		List<BoardVO> boardlist = new ArrayList<>();
		String qry = "select * " //
				+ "   from( " //
				+ "        select rownum rn, tbl_a.* " //
				+ "        from ( " //
				+ "              select board_no, " //
				+ "              title, " //
				+ "              content, " //
				+ "              writer, " //
				+ "              write_date, " //
				+ "              view_cnt " //
				+ "              from tbl_board "; 
		if(search.getSearchCondition().equals("T")) {
			qry += "             where title like '%'||?||'%' "; 
		} else if(search.getSearchCondition().equals("W")){
			qry += "             where writer like '%'||?||'%' ";
		} else if(search.getSearchCondition().equals("TW")){
			qry += "             where title like '%'||?||'%' or writer like '%'||?||'%' ";
		}
		qry += "              order by board_no desc) tbl_a)tbl_b " //
				+ "   where tbl_b.rn >= (? - 1 ) * 5 + 1 and tbl_b.rn <= ? * 5";			
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int cnt = 1;
			psmt = getConnect().prepareStatement(qry);
			// 조건
			if(search.getSearchCondition().equals("T")) { // 제목만 검색
				psmt.setString(cnt++, search.getKeyword());
			} else if(search.getSearchCondition().equals("W")){ // 작성자만 검색
				psmt.setString(cnt++, search.getKeyword());
			} else if(search.getSearchCondition().equals("TW")){ // 제목 + 작성자 검색
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
				
			}
			
			
			psmt.setInt(cnt++, search.getPage());
			psmt.setInt(cnt++, search.getPage());
			rs = psmt.executeQuery();
			// 조회결과 저장
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));

				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return boardlist;
	}

	// 추가 board_no는 시퀀스의 값을 넣기로함.
	public boolean insertBoard(BoardVO board) {
		String sql = "insert into tbl_board (board_no, title, content, writer, img) "
				+ "    values(board_seq.nextval,?,?,?,?)";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());
			psmt.setString(4, board.getImg());

			int r = psmt.executeUpdate(); // insert 실행.
			if (r == 1) {
				return true; // 정상 등록.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false; // 비정상 처리.
	}

	// 수정
	public boolean updateBoard(BoardVO board) {
		String sql = "update tbl_board" + "    set    title = ? " + ",          content = ? "
				+ "    where   board_no = ? ";

		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getBoardNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}

	// 삭제
	public boolean deleteBoard(int boardNo) {
		String query = "delete from tbl_board where board_no= ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, boardNo);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}
}
