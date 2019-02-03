package personal.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.beans.Student;
import personal.service.IStudentService;

public class MyTest {

	private IStudentService service;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (IStudentService) ac.getBean("studentService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01() {
		Student student = new Student("张二", 17);
		service.addStudent(student);
	}
	
	@Test
	public void test02() {
		Student student = new Student("李四", 18);
		student.setId(3);
		service.amendStudent(student);
	}

	@Test
	public void test03() {
		service.removeStudent(1);
	}
	
	@Test
	public void test04() {
		List<Student> students = service.findAllStudents();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void test05() {
		Student student = service.findStudentById(2);
		System.out.println(student);
	}
}
