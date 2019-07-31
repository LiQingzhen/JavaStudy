package personal.dao;

import java.util.List;

import personal.beans.Student;

public interface IStudentDao {
	
	void insertStudent(Student student);
	
	void deleteStudentById(String id);
	
	/**
	 * @param student 根据id，覆盖数据库中原有的数据
	 */
	void updateStudent(Student student);
	
	List<Student> selectAllStudents();
	
	Student selectStudentById(String id);
	
	
}
