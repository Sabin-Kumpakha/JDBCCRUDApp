package com.connect.service;

import com.connect.dto.Student;

public interface iStudentService {
	
	String save(Student student);
	
	Student findById(Integer sid);
	
	String updateById(Integer sid);
	
	String deleteById(Integer sid);
	
}
