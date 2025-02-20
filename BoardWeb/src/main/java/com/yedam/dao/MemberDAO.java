package com.yedam.dao;

import java.sql.SQLException;

import com.yedam.vo.MemberVO;

// 로그인과 관련된것들
public class MemberDAO extends DAO {

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
