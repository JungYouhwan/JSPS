package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.MemberVO;

// 로그인과 관련된것들
public class MemberDAO extends DAO {

	
	
	public boolean updateMember(MemberVO member) {
		String query = "insert into tbl_member(member_id, passwd, member_name)"
				+"      values( ?, ?, ?)";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setString(1, member.getMemberId());
			psmt.setString(2, member.getPasswd());
			psmt.setString(3, member.getMemberName());
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true; // 정상 등록
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteMember(String id) {
		String query = "delete from tbl_member where member_id= ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return true;
	}
	
	
	
	// 조회
	public List<MemberVO> members() {
		String sql = "select member_id," //
				+ "         passwd," //
				+ "         member_name," //
				+ "         responsibility" //
				+ "   from tbl_member " ;
			List<MemberVO> list = new ArrayList<>();
		try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberId(rs.getString("member_id"));
				member.setPasswd(rs.getString("passwd"));
				member.setMemberName(rs.getString("member_name"));
				member.setResponsibility(rs.getString("responsibility"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}
	
	
	public MemberVO login(String id, String pw) {
		String sql = "select member_id," //
				+ "         passwd," //
				+ "         member_name," //
				+ "         responsibility" //
				+ "   from tbl_member " //
				+ "   where member_id = ? " //
				+ "   and   passwd = ? ";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			if (rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberId(rs.getString("member_id"));
				member.setPasswd(rs.getString("passwd"));
				member.setMemberName(rs.getString("member_name"));
				member.setResponsibility(rs.getString("responsibility"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return null;

	}
}
