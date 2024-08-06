package com.connect.controller;

import com.connect.dto.Student;

public interface iStudentController {
	
	//Creating a record
	//Reading a record
	//Updating a record
	//Deleting a record
	
	String save(Student student);
	
	Student findById(Integer sid);
	
	String updateById(Integer sid);
	
	String deleteById(Integer sid);
}
