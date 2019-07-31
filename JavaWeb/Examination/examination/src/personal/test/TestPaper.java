package personal.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.service.IPaperService;

public class TestPaper {

	private IPaperService paperService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		paperService = (IPaperService) ac.getBean("paperService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		paperService.exportPaperById(1, "E:/");
		Set<Integer> randoms = new HashSet<Integer>(10);
		Random random = new Random();
		String choices = "";
		while(randoms.size() < 10){			
			randoms.add(random.nextInt(50) + 1);
		}
		for (Integer integer : randoms) {
			choices = choices + " " + integer;
		}
		choices = choices.trim().replaceAll(" ", ",");	
		System.out.println(choices);
	}

}
