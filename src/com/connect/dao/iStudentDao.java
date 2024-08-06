package com.connect.dao;

import com.connect.dto.Student;

public interface iStudentDao {
	
	String save(Student student);
	
	Student findById(Integer sid);
	
	String updateById(Integer sid);
	
	String deleteById(Integer sid);
}
