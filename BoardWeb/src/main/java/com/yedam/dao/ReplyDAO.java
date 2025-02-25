package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.ReplyVO;

public class ReplyDAO extends DAO{
	
	// 목록
	public List<ReplyVO> replyList(int boardNo){
		String sql = "select reply_no "
				+ "     ,reply "
				+ "     ,replyer "
				+ "     ,board_no "
				+ "     ,reply_date "
				+ "   from tbl_reply"
				+ "   where board_no = ? ";
		List<ReplyVO> list = new ArrayList<>();
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setBoardNo(rs.getInt("board_no"));
				reply.setReplyDate(rs.getDate("reply_date"));
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	// 상세.
	public ReplyVO selectReply(int replyNo) {
		String sql = "select reply_no "
				+ "     ,reply "
				+ "     ,replyer "
				+ "     ,reply_date "
				+ "from tbl_reply "
				+ "where reply_no = ? ";
		ReplyVO reply = new ReplyVO();
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery();
			while(rs.next()) {
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setReplyDate(rs.getDate("reply_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}
	
	// 등록.
	public boolean insertReply(ReplyVO reply) {
		String query1 = "select reply_seq.nextval from dual";
		String query = "insert into tbl_reply(reply_no, reply, replyer, board_no)"
				+"      values ( ?, ?, ?, ?) ";
		try {
			psmt = getConnect().prepareStatement(query1);
			rs = psmt.executeQuery();
			if(rs.next()) {
				reply.setReplyNo(rs.getInt(1)); // 첫번쨰 컬럼.
			}
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply());
			psmt.setString(3, reply.getReplyer());
			psmt.setInt(4, reply.getBoardNo());
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true; // 정상 등록
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 삭제.
	public boolean deleteReply(int replyNo) {
		String query = "delete from tbl_reply where reply_no= ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, replyNo);
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
