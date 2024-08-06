package com.connect.main;

import com.connect.controller.iStudentController;
import com.connect.dto.Student;
import com.connect.factory.StudentControllerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestApp {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		iStudentController studentController = null;
		String status = null, name = null, email = null, city = null, country = null;
		Integer sid = null;
		Student studentRecord = null;

		try {
			while (true) {
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr); 
				
				System.out.println("1, CREATE");
				System.out.println("2, READ");
				System.out.println("3, UPDATE");
				System.out.println("4, DELETE");
				System.out.println("5, EXIT");
				
				System.out.print("Your Option :: [1,2,3,4,5] ::");
				Integer option = Integer.parseInt(br.readLine());

				studentController = StudentControllerFactory.getStudentController();
				
				switch (option) {
				//CREATE
				case 1:
					System.out.print("Enter the name:: ");
					name = br.readLine();
					
					System.out.print("Enter the email:: ");
					email = br.readLine();
					
					System.out.print("Enter the city:: ");
					city = br.readLine();
					
					System.out.print("Enter the country:: ");
					country = br.readLine();
					
					Student student = new Student();
					student.setName(name);
					student.setEmail(email);
					student.setCity(city);
					student.setCountry(country);
					
					status = studentController.save(student);
					if (status.equalsIgnoreCase("success")) {
						System.out.println("Record inserted successfully...");
					} else if (status.equalsIgnoreCase("failure")) {
						System.out.println("Record insertion failed...");
					} else {
						System.out.println("Some problem occured...");
					}
					break;
				//READ
				case 2:
					System.out.print("Enter the student id:: ");
					sid = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(sid);
					
					if (studentRecord != null) {
                        System.out.println(studentRecord);
                    } else {
                        System.out.println("Record not found for the given id ::" + sid);
                        }
					
					break;
				//UPDATE
				case 3:
					System.out.print("Enter the id of the record to be updated:: ");
					sid = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(sid);	//oldRecord
					
					if (studentRecord != null) {
						Student newStudent = new Student();
						newStudent.setSid(sid); //setting the id of the record to be updated 
                        //if record found then update the record
						
						//name
						System.out.print("StudentName :: [Old Name is :: " + studentRecord.getName() + "]: ");
						String newName = br.readLine();
						
						if (newName == null || newName.equals("")) {
							newStudent.setName(studentRecord.getName());
                        } else {
                        	newStudent.setName(newName);
						}
						
						//email
						System.out.print("StudentEmail :: [Old Email is :: " + studentRecord.getEmail() + "]: ");
						String newEmail = br.readLine();
						
						if (newEmail == null || newEmail.equals("")) {
							newStudent.setEmail(studentRecord.getEmail());
                        } else {
                        	newStudent.setEmail(newEmail);
						}
						
						//city
						System.out.print("StudentCity :: [Old City is :: " + studentRecord.getCity() + "]: ");
						String newCity = br.readLine();
						
						if (newCity == null || newCity.equals("")) {
							newStudent.setCity(studentRecord.getCity());
                        } else {
                        	newStudent.setCity(newCity);
						}
						
						//country
						System.out.print("StudentCountry :: [Old Country is :: " + studentRecord.getCountry() + "]: ");
						String newCountry = br.readLine();
						
						if (newCountry == null || newCountry.equals("")) {
							newStudent.setCountry(studentRecord.getCountry());
                        } else {
                        	newStudent.setCountry(newCountry);
						}
						
						 status = studentController.updateById(newStudent);
							if (status.equalsIgnoreCase("success")) {
								System.out.println("Record updated successfully...");
							} else if (status.equalsIgnoreCase("failure")) {
								System.out.println("Record updation failed...");
							} else {
								System.out.println("Some problem occured...");
							}
						
                    } else {
                        System.out.println("Record not found for the given id :: " + sid);
                        }
					
					break;
				//DELETE
				case 4:
					System.out.print("Enter the student id:: ");
					sid = Integer.valueOf(br.readLine());
					status = studentController.deleteById(sid);
					if (status.equalsIgnoreCase("success")) {
                        System.out.println("Record deleted successfully...");
                    } else if (status.equalsIgnoreCase("failure")) {
                        System.out.println("Record deletion failed...");
                    } else {
                        System.out.println("Record not found to delete...");
                        }
					break;
				case 5:
					System.out.println("Thanks for using the application");
					System.exit(0);
				default:
					System.out.println("Please enter the option like 1,2,3,4,5 for operation");
					break;
				}
				
			} 
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		

	}

}
