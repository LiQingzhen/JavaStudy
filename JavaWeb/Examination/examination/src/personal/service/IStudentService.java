package personal.service;

import java.util.List;

import personal.beans.Student;

public interface IStudentService {
	
	void addStudent(Student student);
	
	void removeStudent(String id);
	
	void alertStudent(Student student);
	
	Student findStudentById(String id);
	
	List<Student> findAllStudents();
}
