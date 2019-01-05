package personal.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personal.beans.Student;
import personal.dao.IStudentDao;
import personal.dao.StudentDao;

public class TestStudentDao {
	
	private IStudentDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new StudentDao();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInsertStudent() {
		Student student = new Student("张三", 23, 93.5);
		System.out.println("插入前：" + student);
		dao.insertStudent(student);
		System.out.println("插入后：" + student);
	}
	
	@Test
	public void testDeleteStudentById() {
		dao.deleteStudentById(3);
	}
	
	@Test
	public void testUpdateStudent() {
		Student student = new Student("王五", 22, 97.5);
		student.setId(9);
		dao.updateStudent(student);
	}
	
	@Test
	public void testSelectAllStudents() {
		List<Student> students = dao.selectAllStudents();
		if (students != null) {
			for (Student student : students) {
				System.out.println(student);
			} 
		}
	}
	
	@Test
	public void testSelectStudentById() {
		System.out.println(dao.selectStudentById(4));
	}
	
	@Test
	public void testSelectStudentByName() {
		List<Student> students = dao.selectStudentByName("张");
		if (students != null) {
			for (Student student : students) {
				System.out.println(student);
			} 
		}
	}
	
}
