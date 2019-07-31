package personal.test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.beans.Exam;
import personal.service.IExamService;
import personal.service.IPaperService;

public class TestPaper {

	private IExamService examService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		examService = (IExamService) ac.getBean("examService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<Exam> exams = examService.getExamDim("%2019-05-21%");
		System.out.println(exams.size());
	}

}
