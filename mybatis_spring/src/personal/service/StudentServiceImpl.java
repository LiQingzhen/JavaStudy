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
	public void removeStudent(Integer id) {
		dao.deleteStudent(id);
	}

	@Override
	public void amendStudent(Student student) {
		dao.updateStudent(student);
	}

	@Override
	public List<Student> findAllStudents() {
		return dao.selectAllStudents();
	}

	@Override
	public Student findStudentById(Integer id) {
		return dao.selectStudentById(id);
	}

}
