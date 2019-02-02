package personal.jdbc.service;

import java.util.List;

import personal.jdbc.beans.Student;

public interface IStudentService {

	void addStudent(Student student);
	
	void removeStudent(Integer id);
	
	void amendStudent(Student student);
	
	List<Student> findAllStudents();
	
	Student findStudentById(Integer id);
	
}
