package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	// Connection 객체.
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 DB의 접속정보.
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			// 매개값으로 사용자의 url, 유저정보, 유저 비밀번호
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	} // end of getConnect().
	
	// 상세조회.
	public Employee selectEmp(int empNo) {
		
		String query = "select * from tbl_employees " //
				+ "where emp_no = ?";
		PreparedStatement stmt;
		try {
			stmt = getConnect().prepareStatement(query);
			stmt.setInt(1, empNo);
			
			ResultSet rs = stmt.executeQuery(); // 조회.
			// next = 값이 들어온다면
			if(rs.next()) { // 조회결과가 한건 있으면...
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no")); // 칼럼 값.
				emp.setEmpName(rs.getString("emp_name"));
				emp.setTelNo(rs.getString("tel_no"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				return emp;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // 조회결과 없음.
	}
	
	// 등록
	public boolean registerEmp(Employee emp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "insert into tbl_employees (emp_no, emp_name, tel_no) ";
		// 너무길어서 +=로 뒤에 이어붙임.
		query += "values (" + emp.getEmpNo() // 
		+ ", '"+ emp.getEmpName() //
		+ "', '" + emp.getTelNo()+"')" //
		+ ", " + sdf.format(emp.getHireDate()) // 
		+ ", " + emp.getSalary() + ")";
		try {
			Statement stmt = getConnect().createStatement();
			int r = stmt.executeUpdate(query);
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}// end of registerEmp()

	
	// 사원목록 출력.
	public List<Employee> search(Employee emp) {
		List<Employee> empList = new ArrayList<>();
		String qry = "select * from tbl_employees " //
				+ " where emp_name = nvl('" + emp.getEmpName() + "', emp_name) " // number, varchar2 에 따라 처리.
				+ "order by emp_no";
		
		try {			// Connect() 안에 DB정보를 넣어서 불러왔음.
//			Statement stmt = getConnect().createStatement();
			Statement stmt = getConnect().prepareStatement(qry);
			ResultSet rs = stmt.executeQuery(qry);
			// 조회결과.
			while(rs.next()) {
				Employee empl = new Employee();
				empl.setEmpNo(rs.getInt("emp_no"));
				empl.setEmpName(rs.getString("emp_name"));
				empl.setHireDate(rs.getDate("hire_date"));
				empl.setSalary(rs.getInt("salary"));
				empl.setTelNo(rs.getString("tel_no"));
				
				empList.add(empl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
}
