package personal.service;

import java.util.List;

import personal.beans.Student;

public interface IStudentService {
	
	void addStudent(Student student);
	
	void removeStudent(String id);
	
	void alertStudent(Student student);
	
	Student findStudentById(String id);
	
	/**
	 * 用于分页
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Student> findAllStudents(Integer start, Integer limit);
	
	Integer getCount();
}
