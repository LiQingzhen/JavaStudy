package personal.service;

import java.util.List;

import personal.beans.Student;
import personal.dao.IStudentDao;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao dao;
	
	public IStudentDao getDao() {
		return dao;
	}

	public void setDao(IStudentDao dao) {
		this.dao = dao;
	}

	@Override
	public void addStudent(Student student) {
		dao.insertStudent(student);
	}

	@Override
	public void removeStudent(String id) {
		dao.deleteStudentById(id);
	}

	@Override
	public void alertStudent(Student student) {
		dao.updateStudent(student);
	}

	@Override
	public Student findStudentById(String id) {
		return dao.selectStudentById(id);
	}

	@Override
	public List<Student> findAllStudents() {
		return dao.selectAllStudents();
	}

}
