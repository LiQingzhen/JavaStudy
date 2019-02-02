package personal.jdbc.dao;

import java.util.List;

import personal.jdbc.beans.Student;

public interface IStudentDao {

	void insertStudent(Student student);

	void deleteStudent(Integer id);

	void updateStudent(Student student);

	List<Student> selectAllStudents();

	Student selectStudentById(Integer id);
}
