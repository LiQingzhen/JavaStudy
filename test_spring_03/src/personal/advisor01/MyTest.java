package personal.advisor01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("personal/advisor01/applicationContext.xml");
		ISomeService someService = (ISomeService) ac.getBean("proxyService");
		someService.doFirst();
		System.out.println("================");
		someService.doSecond();
		System.out.println("================");
		someService.doThird();
	}

}
