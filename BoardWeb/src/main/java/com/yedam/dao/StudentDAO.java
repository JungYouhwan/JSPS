package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Student;

public class StudentDAO extends DAO {
	// 학생목록을 반환 메소드. 참고) EmpDAO.search()
	
	
	public boolean addStudent(Student student) {
		// 쿼리문을 sql 변수에 저장
		String sql = "insert into tbl_student(student_no, student_name, phone, address) " //
				+ "values(?, ?, ?, ?) ";
		// psmt 파라메타값을 불러와서 ?에 값을 넣기위해 psmt.setString(순서, 값);
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, student.getStudentNo());
			psmt.setString(2, student.getStudentName());
			psmt.setString(3, student.getPhone());
			psmt.setString(4, student.getAddress());
			
			// 쿼리실행.
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true; // 등록성공.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 등록실패.
	}

	public List<Student> search(Student stud) {
		List<Student> studlist = new ArrayList<>();
		String qry = "select student_no, " 
		+ "                  student_name, " 
		+ "                  phone, " 
		+ "                  address "
		+ "           from tbl_student ";
		try { // Connect() 안에 DB정보를 넣어서 불러왔음.
			stmt = getConnect().prepareStatement(qry);
			rs = stmt.executeQuery(qry);
			// 조회결과.
			while (rs.next()) {
				Student studl = new Student();
				studl.setStudentNo(rs.getString("student_no"));
				studl.setStudentName(rs.getString("student_name"));
				studl.setPhone(rs.getString("phone"));
				studl.setAddress(rs.getString("addrees"));
				
				studlist.add(studl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studlist;
	}
}