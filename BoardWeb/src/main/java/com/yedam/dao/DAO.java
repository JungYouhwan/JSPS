package com.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/*
 * 
 *  EmpDAO, studentDAO를 공통으로 사용하기위해서 만듬.
 */
public class DAO {
	// Connection 객체. Statement, PreparedStatement, ResultSet 기본으로 사용하는 메소드들.

	// 필드로 사용 공통적으로 사용하기떄문.
	Statement stmt; // 쿼리실행하고 결과를 반환하는 클래스.
	PreparedStatement psmt; // 파라메타값을 쉽게 사용하기위해 사용.
	ResultSet rs; // 쿼리결과를 담아두기 위한 set
	Connection conn = null;

	// 세션해제. 사용하지 않으면 닫음
	void disConnect() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 오라클 접속 세션연결.
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 DB의 접속정보.
		String user = "hr";
		String password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			// 매개값으로 사용자의 url, 유저정보, 유저 비밀번호
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	} // end of getConnect().

}