package personal.test;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.beans.Student;
import personal.service.IStudentService;

public class TestStudentDao {

	private IStudentService studentService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentService = (IStudentService) ac.getBean("studentService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Student student = new Student();
		student.setId("2015551122");
		student.setName("李清振");
		student.setPwd("1234567890");
		student.setSex(1);
		student.setGrade("2015");
		student.setClasss("计算机科学与技术1班");
		student.setMajor("JAVA");
		student.setMail("liqingzhen01@126.com");
		student.setPhone("18890300870");
		student.setAddress("山东省聊城市东昌府区");
		student.setRdate(new Timestamp(System.currentTimeMillis()));
		
		studentService.addStudent(student);
		
//		studentService.removeStudent("2015551122");
	}

}
