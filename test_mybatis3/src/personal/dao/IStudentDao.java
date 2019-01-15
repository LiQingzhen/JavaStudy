package personal.dao;

import personal.beans.Student;

public interface IStudentDao {
	
	Student selectStudentById(Integer id);
	
}
