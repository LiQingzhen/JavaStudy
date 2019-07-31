package personal.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.beans.Question;
import personal.service.IQuestionService;

public class TestQuestion {

	private IQuestionService questionService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		questionService = (IQuestionService) ac.getBean("questionService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		questionService.importQuestionsFromExcle("E:/question.xlsx");
	}

}
