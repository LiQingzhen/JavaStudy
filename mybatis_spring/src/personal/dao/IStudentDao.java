package personal.dao;

import java.util.List;

import personal.beans.Student;

public interface IStudentDao {

	void insertStudent(Student student);

	void deleteStudent(Integer id);

	void updateStudent(Student student);

	List<Student> selectAllStudents();

	Student selectStudentById(Integer id);
}
