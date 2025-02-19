package com.yedam.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Employee;


public class EmpDAO extends DAO{
//	// Connection 객체. DAO로 이동시키고 DAO를 상속시킴.
	
	// 상세조회.
	public Employee selectEmp(int empNo) {
		
		String query = "select * from tbl_employees " //
				+ "where emp_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, empNo);
			
			rs = psmt.executeQuery(); // 조회.
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
		String query = "insert into tbl_employees (emp_no, emp_name, tel_no, hire_date, salary) "
		// 너무길어서 +=로 뒤에 이어붙임.
		+ "values (" + emp.getEmpNo() // 
		+ ", '"+ emp.getEmpName() //
		+ "', '" + emp.getTelNo() //
		+ "', '" + sdf.format(emp.getHireDate()) // 
		+ "', " + emp.getSalary() + ")";
		System.out.println(query);
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
				empl.setTelNo(rs.getString("tel_no"));
				empl.setHireDate(rs.getDate("hire_date"));
				empl.setSalary(rs.getInt("salary"));
				
				empList.add(empl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
}
