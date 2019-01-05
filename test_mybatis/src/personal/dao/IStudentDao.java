package personal.dao;

import java.util.List;

import personal.beans.Student;

public interface IStudentDao {
	
	void insertStudent(Student student);
	
	void deleteStudentById(Integer id);
	
	void updateStudent(Student student);
	
	List<Student> selectAllStudents();
	
	Student selectStudentById(Integer id);
	
	// 模糊查询：查询出所有名字包含name的学生的信息
	List<Student> selectStudentByName(String name);
	
}
