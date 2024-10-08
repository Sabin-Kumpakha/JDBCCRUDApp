package com.connect.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connect.dto.Student;
import com.connect.util.JdbcUtil;

public class StudentDaoImpl implements iStudentDao {
	
	Connection connection = null;

	@Override
	public String save(Student student) {
		String sqlInsertQuery = "insert into student(name,email,city,country) values(?,?,?,?)";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			connection = JdbcUtil.getJdbcConnection();	
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getEmail());
				pstmt.setString(3, student.getCity());
				pstmt.setString(4, student.getCountry());
			}
			if (pstmt != null) {
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			status = "failure";
		}
		return status;
	}

	@Override
	public Student findById(Integer sid) {
		String sqlSelectQuery = "select sid, name, email, city, country from student where sid = ? ";
		PreparedStatement pstmt = null;
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();	
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
			}
			if (pstmt != null) {
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					//copy the resultset data to StudentDTO and transfer it to view
					student = new Student();
					student.setSid(resultSet.getInt("sid"));
					student.setName(resultSet.getString("name"));
					student.setEmail(resultSet.getString("email"));
					student.setCity(resultSet.getString("city"));
					student.setCountry(resultSet.getString("country"));
				} 
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
		return student;
	}

	@Override
	public String updateById(Student student) {
		String sqlUpdateQuery = "update student set name = ?, email = ?, city = ?, country = ? where sid = ? ";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			connection = JdbcUtil.getJdbcConnection();	
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getEmail());
				pstmt.setString(3, student.getCity());
				pstmt.setString(4, student.getCountry());
				pstmt.setInt(5, student.getSid());
			}
			if (pstmt != null) {
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			status = "failure";
		}
		return status;
	}

	@Override
	public String deleteById(Integer sid) {
		String sqlDeleteQuery = "delete from student where sid = ? ";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			Student student = findById(sid);
			if (student != null) {
				
				connection = JdbcUtil.getJdbcConnection();	
				if(connection != null) 
					pstmt = connection.prepareStatement(sqlDeleteQuery);
				if (pstmt != null) 
					pstmt.setInt(1, sid);
				if (pstmt != null) {
					int rowAffected = pstmt.executeUpdate();
					if (rowAffected == 1) {
						status = "success";
					} else {
						status = "failure";
					}
					
				}
			//else student is null 
			} else {
				status = "no record found";
			}
		
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			status = "failure";
		}
		return status;
	}

}
